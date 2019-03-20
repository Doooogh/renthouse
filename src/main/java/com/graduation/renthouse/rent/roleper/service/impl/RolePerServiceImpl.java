package com.graduation.renthouse.rent.roleper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.roleper.dao.RolePerDao;
import com.graduation.renthouse.rent.roleper.domain.RolePerDO;
import com.graduation.renthouse.rent.roleper.service.RolePerService;



@Service
public class RolePerServiceImpl implements RolePerService {
	@Autowired
	private RolePerDao rolePerDao;
	
	@Override
	public RolePerDO get(Integer id){
		return rolePerDao.get(id);
	}
	
	@Override
	public List<RolePerDO> list(Map<String, Object> map){
		return rolePerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return rolePerDao.count(map);
	}
	
	@Override
	public int save(RolePerDO rolePer){
		return rolePerDao.save(rolePer);
	}
	
	@Override
	public int update(RolePerDO rolePer){
		return rolePerDao.update(rolePer);
	}
	
	@Override
	public int remove(Integer id){
		return rolePerDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return rolePerDao.batchRemove(ids);
	}
	
}
