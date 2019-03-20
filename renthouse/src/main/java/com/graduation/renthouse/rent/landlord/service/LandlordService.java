package com.graduation.renthouse.rent.landlord.service;

import com.graduation.renthouse.rent.landlord.domain.LandlordDO;
import com.graduation.renthouse.rent.landlord.domain.LandlordVO;
import com.graduation.renthouse.system.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-22 17:01:39
 */
public interface LandlordService {
	
	LandlordDO get(Integer id);
	
	List<LandlordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LandlordDO landlord);
	
	int update(LandlordDO landlord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	PageUtils listByLandlord(Map<String, Object> map);
}
