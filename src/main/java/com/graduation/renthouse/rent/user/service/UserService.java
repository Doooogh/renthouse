package com.graduation.renthouse.rent.user.service;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.user.domain.UserDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-11 15:01:29
 */
public interface UserService {
	
	UserDO get(Integer userId);
	
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Integer userId);
	
	int batchRemove(Integer[] userIds);

	List<UserDO> selectByUsername(String username);

	UserDO getByUsername(String username);

	Set<String> getRoles(String username);

	Set<String> getPermissions(String username);

	Set<PermissionDO> getPermissionsByUserId(Integer userId);

	List<Tree<PermissionDO>> getTree(Set<PermissionDO> permissions);

}

