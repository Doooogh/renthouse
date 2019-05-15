package com.graduation.renthouse.rent.house.service.impl;

import com.graduation.renthouse.system.annotation.InsertLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.house.dao.HouseDao;
import com.graduation.renthouse.rent.house.domain.HouseDO;
import com.graduation.renthouse.rent.house.service.HouseService;



@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseDao houseDao;
	
	@Override
	public HouseDO get(Integer id){
		return houseDao.get(id);
	}
	
	@Override
	public List<HouseDO> list(Map<String, Object> map){
		return houseDao.list(map);
	}

    @Override
    public List<HouseDO> findByMore(Map<String, Object> map) {
        return houseDao.findByMore(map);
    }

    @Override
    public List<HouseDO> findByTitle(Map<String, Object> map) {
        return houseDao.findByTitle(map);
    }

    @Override
	public int count(Map<String, Object> map){
		return houseDao.count(map);
	}

	@Override
	public int countByMore(Map<String, Object> map) {
		return houseDao.countByMore(map);
	}

	@Override
	public int countByTitle(Map<String, Object> map) {
		return houseDao.countByTitle(map);
	}

	@Override
	@InsertLog("house")
	public int save(HouseDO house){
		return houseDao.save(house);
	}
	
	@Override
	public int update(HouseDO house){
		return houseDao.update(house);
	}
	
	@Override
	public int remove(Integer id){
		return houseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return houseDao.batchRemove(ids);
	}
	
}
