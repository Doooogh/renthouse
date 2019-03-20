package com.graduation.renthouse.rent.roleper.dao;

import com.graduation.renthouse.rent.role.domain.RoleDO;
import com.graduation.renthouse.rent.roleper.domain.RolePerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-13 15:54:55
 */
@Mapper
public interface RolePerDao {

	RolePerDO get(Integer id);
	
	List<RolePerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RolePerDO rolePer);
	
	int update(RolePerDO rolePer);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<Integer> listPerIdByRoleId(Integer roleId);



	int deletePerByRoleId(Integer roleId);

	int batchSave(List<RolePerDO> rolePers);

	List<Integer>selectPerIdByRoleIds(List<Integer> roles);

}
