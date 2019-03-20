package com.graduation.renthouse.rent.permission.service.impl;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.rent.roleper.dao.RolePerDao;
import com.graduation.renthouse.rent.userrole.dao.UserRoleDao;
import com.graduation.renthouse.system.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.graduation.renthouse.rent.permission.dao.PermissionDao;
import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.permission.service.PermissionService;



@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private RolePerDao rolePerDao;

	@Autowired
	private UserRoleDao userRoleDao;

	
	@Override
	public PermissionDO get(Integer id){
		return permissionDao.get(id);
	}
	
	@Override
	public List<PermissionDO> list(Map<String, Object> map){
		return permissionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return permissionDao.count(map);
	}
	
	@Override
	public int save(PermissionDO permission){
		return permissionDao.save(permission);
	}
	
	@Override
	public int update(PermissionDO permission){
		return permissionDao.update(permission);
	}
	
	@Override
	public int remove(Integer id){
		return permissionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return permissionDao.batchRemove(ids);
	}

	@Override
	public void recursion(Integer id) {
		permissionDao.remove(id);
		Map<String,Object> map=new HashMap<>();
		map.put("parentId",id);
		List<PermissionDO> list = permissionDao.list(map);
		System.out.println(list.size()+"---------------------------");
		while (list.size()!=0){
			for (PermissionDO permissionDO : list) {
				permissionDao.remove(permissionDO.getId());
				recursion(permissionDO.getId());
			}
		}

	}

	@Override
	public Tree<PermissionDO> getTree(Integer roleId) {
		//所有的权限
		List<PermissionDO> pers = permissionDao.list(new HashMap<String, Object>(16));
		// 根据roleId查询权限(该角色的权限)
		List<Integer> rpers =rolePerDao.listPerIdByRoleId(roleId);
		System.out.println(rpers);
		List<Integer> temp = rpers;
		for (PermissionDO per : pers) {
			if (temp.contains(per.getParentId())) {
				rpers.remove(per.getParentId());
			}
		}
		List<Tree<PermissionDO>> trees = new ArrayList<Tree<PermissionDO>>();
		List<PermissionDO> permissionDOs = permissionDao.list(new HashMap<String, Object>(16));
		for (PermissionDO per : permissionDOs) {
			Tree<PermissionDO> tree = new Tree<PermissionDO>();
			tree.setId(per.getId().toString());
			tree.setParentId(per.getParentId().toString());
			tree.setText(per.getName());
			Map<String, Object> state = new HashMap<>(16);
			Integer perId = per.getId();
			if (rpers.contains(perId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<PermissionDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<PermissionDO> getTree() {
		List<PermissionDO> perList = permissionDao.list(new HashMap<>());
		List<Tree<PermissionDO>> trees = new ArrayList<Tree<PermissionDO>>();
		for (PermissionDO per : perList) {
			Tree<PermissionDO> tree=new Tree<>();
			tree.setId(per.getId().toString());
			tree.setParentId(per.getParentId().toString());
			tree.setText(per.getName());
			Map<String, Object> state = new HashMap<>(16);
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url",per.getUrl());
			attributes.put("img",per.getImg());
			attributes.put("type",per.getType());
			tree.setAttributes(attributes);
			Integer perId = per.getId();
			state.put("selected", false);
			tree.setState(state);
			trees.add(tree);
		}
		Tree<PermissionDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<PermissionDO> getTreeByUserId(Integer userId) {
		List<Integer> roleIds=userRoleDao.selectRoleIdByUserId(userId);

		return null;
	}

}
