package com.graduation.renthouse.rent.insertlog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.insertlog.dao.InsertLogDao;
import com.graduation.renthouse.rent.insertlog.domain.InsertLogDO;
import com.graduation.renthouse.rent.insertlog.service.InsertLogService;



@Service
public class InsertLogServiceImpl implements InsertLogService {
	@Autowired
	private InsertLogDao insertLogDao;
	
	@Override
	public InsertLogDO get(Integer id){
		return insertLogDao.get(id);
	}
	
	@Override
	public List<InsertLogDO> list(Map<String, Object> map){
		return insertLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return insertLogDao.count(map);
	}
	
	@Override
	public int save(InsertLogDO insertLog){
		return insertLogDao.save(insertLog);
	}
	
	@Override
	public int update(InsertLogDO insertLog){
		return insertLogDao.update(insertLog);
	}
	
	@Override
	public int remove(Integer id){
		return insertLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return insertLogDao.batchRemove(ids);
	}
	
}
