package com.graduation.renthouse.rent.tag.service;

import com.graduation.renthouse.rent.tag.domain.TagDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-26 11:08:07
 */
public interface TagService {
	
	TagDO get(Integer id);
	
	List<TagDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TagDO tag);
	
	int update(TagDO tag);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
