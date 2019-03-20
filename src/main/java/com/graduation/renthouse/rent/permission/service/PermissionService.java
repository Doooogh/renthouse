package com.graduation.renthouse.rent.permission.service;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.rent.permission.domain.PermissionDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-11 15:04:00
 */
public interface PermissionService {
	
	PermissionDO get(Integer id);
	
	List<PermissionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PermissionDO permission);
	
	int update(PermissionDO permission);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	void  recursion(Integer id);

	Tree<PermissionDO> getTree(Integer roleId);

	Tree<PermissionDO> getTree();

	Tree<PermissionDO> getTreeByUserId(Integer userId);
}
