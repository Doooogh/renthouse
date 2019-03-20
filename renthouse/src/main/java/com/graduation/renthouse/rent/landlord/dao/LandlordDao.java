package com.graduation.renthouse.rent.landlord.dao;

import com.graduation.renthouse.rent.landlord.domain.LandlordDO;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.landlord.domain.LandlordVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-22 17:01:39
 */
@Mapper
public interface LandlordDao {

	LandlordDO get(Integer id);
	
	List<LandlordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LandlordDO landlord);
	
	int update(LandlordDO landlord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);


}
