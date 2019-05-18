package com.graduation.renthouse.rent.tag.service.impl;

import com.graduation.renthouse.rent.tag.dao.TagHouseDao;
import com.graduation.renthouse.rent.tag.domain.TagDO;
import com.graduation.renthouse.rent.tag.domain.TagHouse;
import com.graduation.renthouse.rent.tag.service.TagHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagHouseServiceImpl implements TagHouseService {

    @Autowired
    private TagHouseDao tagHouseDao;




    @Override
    public List<TagHouse> list(Map<String, Object> map) {
        return tagHouseDao.list(map);
    }

    @Override
    public int batchRemove(List<Integer> ids) {
        return tagHouseDao.batchRemove(ids);
    }

    @Override
    public int batchSave(List<TagHouse> tagHouses) {
        return tagHouseDao.batchSave(tagHouses);
    }

    @Override
    public List<Integer> getTagIds(Integer houseId) {
        Map<String,Object> map=new HashMap<>();
        map.put("houseId",houseId);
        List<Integer>  tagHouseIds=new ArrayList<>();
        for (TagHouse tagHouse : tagHouseDao.list(map)) {
            tagHouseIds.add(tagHouse.getId());
        }
        return  tagHouseIds;
    }

    @Override
    public List<TagDO> getTagsByHouseId(Integer houseId) {
        Map<String,Object> map=new HashMap<>();
        map.put("houseId",houseId);
        tagHouseDao.getTagsByHouseId(map);
        return tagHouseDao.getTagsByHouseId(map);
    }
}
