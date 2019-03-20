package com.graduation.renthouse.rent.userrole.dao;

import com.graduation.renthouse.rent.role.domain.RoleDO;
import com.graduation.renthouse.rent.userrole.domain.UserRoleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-13 16:12:50
 */
@Mapper
public interface UserRoleDao {

	UserRoleDO get(Integer id);
	
	List<UserRoleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserRoleDO userRole);
	
	int update(UserRoleDO userRole);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int batchSave(List<UserRoleDO> userRoles);

	int deleteByUserId(Integer userId);

	List<Integer> selectRoleIdByUserId(Integer userId);
}
