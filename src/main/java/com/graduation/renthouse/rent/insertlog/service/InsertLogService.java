package com.graduation.renthouse.rent.insertlog.service;

import com.graduation.renthouse.rent.insertlog.domain.InsertLogDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Li
 * @email 1083460362@qq.com
 * @date 2019-05-08 23:55:35
 */
public interface InsertLogService {
	
	InsertLogDO get(Integer id);
	
	List<InsertLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(InsertLogDO insertLog);
	
	int update(InsertLogDO insertLog);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
