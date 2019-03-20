package com.graduation.renthouse.rent.userrole.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.userrole.dao.UserRoleDao;
import com.graduation.renthouse.rent.userrole.domain.UserRoleDO;
import com.graduation.renthouse.rent.userrole.service.UserRoleService;



@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public UserRoleDO get(Integer id){
		return userRoleDao.get(id);
	}
	
	@Override
	public List<UserRoleDO> list(Map<String, Object> map){
		return userRoleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userRoleDao.count(map);
	}
	
	@Override
	public int save(UserRoleDO userRole){
		return userRoleDao.save(userRole);
	}
	
	@Override
	public int update(UserRoleDO userRole){
		return userRoleDao.update(userRole);
	}
	
	@Override
	public int remove(Integer id){
		return userRoleDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userRoleDao.batchRemove(ids);
	}

	@Override
	public List<Integer> selectRoleIdByUserId(Integer userId) {
		return userRoleDao.selectRoleIdByUserId(userId);
	}

}
