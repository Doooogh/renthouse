package com.graduation.renthouse.rent.house.dao;

import com.graduation.renthouse.rent.house.domain.HouseDO;
import com.graduation.renthouse.rent.house.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseDaoTest {


    @Autowired
    private HouseService houseService;

    @Test
    public void list() {
        Map<String,Object> map=new HashMap<>();
        List<HouseDO> list = houseService.list(map);
        System.out.println(list.size());
        for (HouseDO houseDO : list) {
            System.out.println(houseDO);
        }
    }
}