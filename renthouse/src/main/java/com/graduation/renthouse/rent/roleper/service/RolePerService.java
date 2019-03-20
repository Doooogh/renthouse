package com.graduation.renthouse.rent.roleper.service;

import com.graduation.renthouse.rent.roleper.domain.RolePerDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-13 15:54:55
 */
public interface RolePerService {
	
	RolePerDO get(Integer id);
	
	List<RolePerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RolePerDO rolePer);
	
	int update(RolePerDO rolePer);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
