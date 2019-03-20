package com.graduation.renthouse.rent.tag.dao;

import com.graduation.renthouse.rent.tag.domain.TagDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-26 11:08:07
 */
@Mapper
public interface TagDao {

	TagDO get(Integer id);
	
	List<TagDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TagDO tag);
	
	int update(TagDO tag);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
