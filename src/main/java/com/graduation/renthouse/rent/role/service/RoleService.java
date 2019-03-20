package com.graduation.renthouse.rent.role.service;

import com.graduation.renthouse.rent.role.domain.RoleDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-11 15:06:07
 */
public interface RoleService {
	
	RoleDO get(Integer roleId);
	
	List<RoleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(Integer roleId);
	
	int batchRemove(Integer[] roleIds);

	String getTime(String param);
}
