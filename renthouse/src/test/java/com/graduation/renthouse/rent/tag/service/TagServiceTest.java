package com.graduation.renthouse.rent.tag.service;

import com.graduation.renthouse.rent.tag.domain.TagDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void list() {

        Map<String,Object> map=new HashMap<>();
        map.put("houseId",1);
        List<TagDO> list = tagService.list(map);
        for (TagDO tag : list) {
            System.out.println(tag.getHouseId());
        }
    }
}