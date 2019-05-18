package com.graduation.renthouse.rent.tag.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.tag.dao.TagDao;
import com.graduation.renthouse.rent.tag.domain.TagDO;
import com.graduation.renthouse.rent.tag.service.TagService;



@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagDao tagDao;
	
	@Override
	public TagDO get(Integer id){
		return tagDao.get(id);
	}
	
	@Override
	public List<TagDO> list(Map<String, Object> map){
		return tagDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tagDao.count(map);
	}
	
	@Override
	public int save(TagDO tag){
		return tagDao.save(tag);
	}
	
	@Override
	public int update(TagDO tag){
		return tagDao.update(tag);
	}
	
	@Override
	public int remove(Integer id){
		return tagDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tagDao.batchRemove(ids);
	}



}
