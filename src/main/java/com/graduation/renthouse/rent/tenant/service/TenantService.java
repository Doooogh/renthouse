package com.graduation.renthouse.rent.tenant.service;

import com.graduation.renthouse.rent.tenant.domain.TenantDO;
import com.graduation.renthouse.system.annotation.InsertLog;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-23 10:43:10
 */
public interface TenantService {
	
	TenantDO get(Integer id);
	
	List<TenantDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int save(TenantDO tenant);
	
	int update(TenantDO tenant);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
