package com.graduation.renthouse.rent.role.service.impl;

import com.graduation.renthouse.rent.roleper.dao.RolePerDao;
import com.graduation.renthouse.rent.roleper.domain.RolePerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.role.dao.RoleDao;
import com.graduation.renthouse.rent.role.domain.RoleDO;
import com.graduation.renthouse.rent.role.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RolePerDao rolePerDao;
	
	@Override
	public RoleDO get(Integer roleId){
		return roleDao.get(roleId);
	}
	
	@Override
	public List<RoleDO> list(Map<String, Object> map){
		return roleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roleDao.count(map);
	}
	
	@Override
	public int save(RoleDO role){
		int a=roleDao.save(role);
		Integer roleId=role.getRoleId();
		rolePerDao.deletePerByRoleId(roleId);
		List<Integer> perIds = role.getPerIds();
		List<RolePerDO> rolePers=new ArrayList<>();
		for (Integer perId : perIds) {
			RolePerDO rolePer=new RolePerDO();
			rolePer.setPerId(perId);
			rolePer.setRoleId(roleId);
			rolePers.add(rolePer);
		}
		int b=rolePerDao.batchSave(rolePers);
		if(a>0&&b>0){
			return 1;
		}
		return 0;

	}
	
	@Override
	public int update(RoleDO role){
		Integer roleId=role.getRoleId();
		int t=roleDao.update(role);
		int a=rolePerDao.deletePerByRoleId(roleId);
		List<Integer> perIds = role.getPerIds();
		List<RolePerDO> rolePerDOS=new ArrayList<>();
		for (Integer perId : perIds) {
			RolePerDO rolePer=new RolePerDO();
			rolePer.setRoleId(roleId);
			rolePer.setPerId(perId);
			rolePerDOS.add(rolePer);
		}
		if(rolePerDOS.size()>0){
			rolePerDao.batchSave(rolePerDOS);
		}
		return t;
	}
	
	@Override
	public int remove(Integer roleId){
		return roleDao.remove(roleId);
	}
	
	@Override
	public int batchRemove(Integer[] roleIds){
		return roleDao.batchRemove(roleIds);
	}

	@Override
	@Cacheable(value = "user", key = "#param")
	public String getTime(String param) {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString();
	}

}
