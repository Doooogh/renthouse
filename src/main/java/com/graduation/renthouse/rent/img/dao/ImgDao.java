package com.graduation.renthouse.rent.img.dao;

import com.graduation.renthouse.rent.img.domain.ImgDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-26 11:04:54
 */
@Mapper
public interface ImgDao {

	ImgDO get(Integer id);
	
	List<ImgDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImgDO img);
	
	int update(ImgDO img);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
