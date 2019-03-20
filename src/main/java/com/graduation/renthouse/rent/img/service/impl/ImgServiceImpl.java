package com.graduation.renthouse.rent.img.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.img.dao.ImgDao;
import com.graduation.renthouse.rent.img.domain.ImgDO;
import com.graduation.renthouse.rent.img.service.ImgService;



@Service
public class ImgServiceImpl implements ImgService {
	@Autowired
	private ImgDao imgDao;
	
	@Override
	public ImgDO get(Integer id){
		return imgDao.get(id);
	}
	
	@Override
	public List<ImgDO> list(Map<String, Object> map){
		return imgDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imgDao.count(map);
	}
	
	@Override
	public int save(ImgDO img){
		return imgDao.save(img);
	}
	
	@Override
	public int update(ImgDO img){
		return imgDao.update(img);
	}
	
	@Override
	public int remove(Integer id){
		return imgDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return imgDao.batchRemove(ids);
	}
	
}
