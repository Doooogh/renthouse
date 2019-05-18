package com.graduation.renthouse.rent.tag.dao;

import com.graduation.renthouse.rent.tag.domain.TagDO;
import com.graduation.renthouse.rent.tag.domain.TagHouse;

import java.util.List;
import java.util.Map;

public interface TagHouseDao {


    List<TagHouse>  list(Map<String,Object> map);

    int batchRemove(List<Integer> ids);

    int batchSave(List<TagHouse> tagHouses);

    List<TagDO> getTagsByHouseId(Map<String,Object> map);

}
