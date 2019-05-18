package com.graduation.renthouse.rent.tag.service;

import com.graduation.renthouse.rent.tag.domain.TagDO;
import com.graduation.renthouse.rent.tag.domain.TagHouse;

import java.util.List;
import java.util.Map;

public interface TagHouseService {
    List<TagHouse> list(Map<String,Object> map);

    int batchRemove(List<Integer> ids);

    int batchSave(List<TagHouse> tagHouses);

    List<Integer> getTagIds(Integer houseId);

    List<TagDO> getTagsByHouseId(Integer houseId);
}
