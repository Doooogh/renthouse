package com.graduation.renthouse.rent.user.service.impl;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.userrole.dao.UserRoleDao;
import com.graduation.renthouse.rent.userrole.domain.UserRoleDO;
import com.graduation.renthouse.system.utils.BuildTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.graduation.renthouse.rent.user.dao.UserDao;
import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.rent.user.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public UserDO get(Integer userId){
		return userDao.get(userId);
	}
	
	@Override
	public List<UserDO> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UserDO user){
		int b=userDao.save(user);
		Integer userId=user.getUserId();
		userRoleDao.deleteByUserId(userId);
		List<Integer> roleIds = user.getRoleIds();
		List<UserRoleDO> userRoles=new ArrayList<>();
		for (Integer roleId : roleIds) {
			UserRoleDO userRole=new UserRoleDO();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoles.add(userRole);
		}
		for (UserRoleDO userRole : userRoles) {
			System.out.println(userRole.getUserId()+",,,"+userRole.getRoleId());
		}
		int a=0;
		System.out.println(userRoles.size()+"======================");
		if(userRoles.size()>0) {
		 a = userRoleDao.batchSave(userRoles);
		}
		if(a>0&&b>0){
			return 1;
		}
		return 0;
	}
	
	@Override
	public int update(UserDO user){
		Integer userId=user.getUserId();
		int a=userDao.update(user);
		userRoleDao.deleteByUserId(userId);
		for (Integer integer : user.getRoleIds()) {
			System.out.println(integer+"------------------------------");
		}
		List<Integer> roleIds=user.getRoleIds();
		List<UserRoleDO> userRoles=new ArrayList<>();
		for (Integer roleId : roleIds) {
			UserRoleDO userRole=new UserRoleDO();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRoles.add(userRole);
		}
		int v=userRoleDao.batchSave(userRoles);
		if(a>0&&v>0){
			return 1;
		}
		return 0;
	}
	
	@Override
	public int remove(Integer userId){
		return userDao.remove(userId);
	}
	
	@Override
	public int batchRemove(Integer[] userIds){
		return userDao.batchRemove(userIds);
	}

	@Override
	public List<UserDO> selectByUsername(String username) {
		return userDao.selectByUsername(username);
	}

	@Override
	public UserDO getByUsername(String username) {
		return userDao.getByUsername(username);
	}

	@Override
	public Set<String> getRoles(String username) {
		return userDao.getRoles(username);
	}

	@Override
	public Set<String> getPermissions(String username) {
		Set<String> permissions = userDao.getPermissions(username);
		Set<String> pers=new HashSet<>();
		for (String permission : permissions) {
			if(StringUtils.isNotBlank(permission)){
				pers.add(permission);
			}
		}
		return pers;
	}

	@Override
	public Set<PermissionDO> getPermissionsByUserId(Integer userId) {
		return userDao.getPermissionsByUserId(userId);
	}

	@Override
	public  List<Tree<PermissionDO>> getTree(Set<PermissionDO> permissions) {
		List<PermissionDO>perList=new ArrayList<>(permissions);
		List<Tree<PermissionDO>> trees=new ArrayList<>();
		for (PermissionDO per : perList) {
			if(per.getType()==2){
				continue;
			}
			Tree<PermissionDO> tree=new Tree<>();
			tree.setId(per.getId().toString());
			tree.setParentId(per.getParentId().toString());
			tree.setText(per.getName());
			Map<String, Object> state = new HashMap<>(16);
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url",per.getUrl());
			attributes.put("img",per.getImg());
			attributes.put("type",per.getType());
			if(per.getSort()!=null){
				attributes.put("sort",per.getSort());
			}
			tree.setAttributes(attributes);
			Integer perId = per.getId();
			state.put("selected", false);
			tree.setState(state);
			trees.add(tree);
		}
		return trees;
	}

}
