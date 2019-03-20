package com.graduation.renthouse.rent.house.service;

import com.graduation.renthouse.rent.house.domain.HouseDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-22 17:18:56
 */
public interface HouseService {
	
	HouseDO get(Integer id);
	
	List<HouseDO> list(Map<String, Object> map);

    List<HouseDO> findByMore(Map<String,Object> map);

    List<HouseDO> findByTitle(Map<String,Object> map);

	int count(Map<String, Object> map);

	int countByMore(Map<String, Object> map);

	int countByTitle(Map<String, Object> map);
	
	int save(HouseDO house);
	
	int update(HouseDO house);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
