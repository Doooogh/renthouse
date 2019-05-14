package com.graduation.renthouse.system.utils;

import com.graduation.renthouse.rent.common.entity.Tree;

import java.util.*;

public class BuildTree {

	public static <T> Tree<T> build(List<Tree<T>> nodes) {

		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

		for (Tree<T> children : nodes) {

			String pid = children.getParentId();
			if (pid == null || "0".equals(pid)) {
				topNodes.add(children);

				continue;
			}

			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					continue;
				}
			}

		}

		Tree<T> root = new Tree<T>();
		if (topNodes.size() == 1) {
			root = topNodes.get(0);
		} else {
			root.setId("-1");
			root.setParentId("");
			root.setHasParent(false);
			root.setChildren(true);
			root.setChecked(true);
			root.setChildren(topNodes);
			root.setText("顶级节点");
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			root.setState(state);
		}

		return root;
	}

	public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<Tree<T>>();

		for (Tree<T> children : nodes) {

			String pid = children.getParentId();
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(children);

				continue;
			}

			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);

					continue;
				}
			}

		}

			Collections.sort(topNodes, new Comparator<Tree<T>>() {
				@Override
				public int compare(Tree<T> o1, Tree<T> o2) {

					//按照Person的年龄进行升序排列
					Integer o1Sort= (Integer) o1.getAttributes().get("sort");
					Integer o2Sort= (Integer) o2.getAttributes().get("sort");
					if(o1Sort==null){
						o1Sort=0;
					}
					if(o2Sort==null){
						o2Sort=0;
					}
					if(o1Sort > o2Sort){
						return 1;
					}
					if(o1Sort == o2Sort){
						return 0;
					}
					return -1;

				}
			});


		return topNodes;
	}

	public static <T>List<Tree<T>> getListTree(){
		return null;
	}

}