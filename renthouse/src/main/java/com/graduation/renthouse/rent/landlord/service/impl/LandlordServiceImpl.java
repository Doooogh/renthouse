package com.graduation.renthouse.rent.landlord.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;
import com.graduation.renthouse.rent.house.dao.HouseDao;
import com.graduation.renthouse.rent.house.domain.HouseDO;
import com.graduation.renthouse.rent.landlord.domain.LandlordVO;
import com.graduation.renthouse.system.comment.Constant;
import com.graduation.renthouse.system.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.landlord.dao.LandlordDao;
import com.graduation.renthouse.rent.landlord.domain.LandlordDO;
import com.graduation.renthouse.rent.landlord.service.LandlordService;



@Service
public class LandlordServiceImpl implements LandlordService {
	@Autowired
	private LandlordDao landlordDao;

	@Autowired
	private HouseDao houseDao;
	
	@Override
	public LandlordDO get(Integer id){
		return landlordDao.get(id);
	}
	
	@Override
	public List<LandlordDO> list(Map<String, Object> map){
		return landlordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return landlordDao.count(map);
	}
	
	@Override
	public int save(LandlordDO landlord){
		return landlordDao.save(landlord);
	}
	
	@Override
	public int update(LandlordDO landlord){
		return landlordDao.update(landlord);
	}
	
	@Override
	public int remove(Integer id){
		return landlordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return landlordDao.batchRemove(ids);
	}

	@Override
	public PageUtils listByLandlord(Map<String, Object> map) {

		// 更改格式  例如:userId  变为 user_id
		Object s = map.get(Constant.SORT);
		if (s != null) {
			map.put(Constant.SORT, CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s.toString()));
		}
		//实现分页
		int offset = Integer.parseInt(map.get(Constant.OFFSET).toString());
		int limit = Integer.parseInt(map.get(Constant.LIMIT).toString());
		/*if (!ShiroUtils.isAllData()) {
			map.put("data", ShiroUtils.getUserId());
		}*/

		List<HouseDO> houses = houseDao.list(new HashMap<>());
		List<LandlordDO> landlords = landlordDao.list(new HashMap<>());
		List<LandlordVO> landlordVOList=new ArrayList<>();
		for (LandlordDO landlord : landlords) {
			List<HouseDO>houseList=new ArrayList<>();
			LandlordVO landlordVO=new LandlordVO();
			landlordVO.setId(landlord.getId());
			landlordVO.setName(landlord.getName());
			for (HouseDO house : houses) {
				if(landlord.getId()==house.getLandlordId()){
					houseList.add(house);
				}
			}
			landlordVO.setHouses(houseList);
			landlordVOList.add(landlordVO);
		}
		PageHelper.offsetPage(offset, limit);
		PageInfo pageInfo = new PageInfo(landlordVOList);
		PageUtils pageUtils = new PageUtils(pageInfo.getList(), (int) pageInfo.getTotal());

		return pageUtils;
	}

}
