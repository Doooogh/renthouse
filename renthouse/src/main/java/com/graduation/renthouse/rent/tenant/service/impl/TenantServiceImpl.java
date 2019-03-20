package com.graduation.renthouse.rent.tenant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.tenant.dao.TenantDao;
import com.graduation.renthouse.rent.tenant.domain.TenantDO;
import com.graduation.renthouse.rent.tenant.service.TenantService;



@Service
public class TenantServiceImpl implements TenantService {
	@Autowired
	private TenantDao tenantDao;
	
	@Override
	public TenantDO get(Integer id){
		return tenantDao.get(id);
	}
	
	@Override
	public List<TenantDO> list(Map<String, Object> map){
		return tenantDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tenantDao.count(map);
	}
	
	@Override
	public int save(TenantDO tenant){
		return tenantDao.save(tenant);
	}
	
	@Override
	public int update(TenantDO tenant){
		return tenantDao.update(tenant);
	}
	
	@Override
	public int remove(Integer id){
		return tenantDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tenantDao.batchRemove(ids);
	}
	
}
