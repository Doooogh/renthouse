package com.graduation.renthouse.rent.landlord.dao;

import com.graduation.renthouse.rent.landlord.domain.LandlordVO;

import java.util.List;
import java.util.Map;

public interface LandlordVODao {

    List<LandlordVO> list(Map<String, Object> map);
}
