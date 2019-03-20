package com.graduation.renthouse.service.impl;

import com.graduation.renthouse.rent.house.domain.House;
import com.graduation.renthouse.rent.house.domain.HouseDO;
import com.graduation.renthouse.rent.house.service.HouseService;
import com.graduation.renthouse.rent.landlord.service.LandlordService;
import com.graduation.renthouse.rent.tenant.service.TenantService;
import com.graduation.renthouse.system.utils.DateUtils;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseServiceImplTest {

    @Autowired
    private HouseService houseService;

    @Autowired
    private LandlordService landlordService;

    @Autowired
    private TenantService tenantService;


    @Test
    public void list() {
        System.out.println(houseService.list(new HashMap<>()).size());
    }

    @Test
    public  void test1(){
        HouseDO  house=new HouseDO();
        house.setTitle("测试标题");
        house.setAddress("测试地址");
        house.setAddTime("测试时间");
        house.setPrice(33);
        house.setStatus(0);
        int a=houseService.save(house);
        System.out.println(a);

    }

    @Test
    public void test2(){
        System.out.println(houseService.get(1));
    }


    @Test
    public void test3(){
        HouseDO houseDO=new HouseDO();
        houseDO.setStatus(1);
        houseDO.setTitle("测试一");
        houseDO.setLandlordId(1);
        houseDO.setTenantId(1);
        System.out.println(houseDO);

        House house=new House();
        String landlordName=landlordService.get(houseDO.getLandlordId()).getName();
        String tenantName=tenantService.get(houseDO.getTenantId()).getName();
        BeanUtils.copyProperties(houseDO,house);
        house.setLandlordName(landlordName);
        house.setTenantName(tenantName);

        System.out.println(house);
    }

    @Test
    public void test4(){
       HouseDO houseDO= houseService.get(1);
       Date date=houseDO.getCreatetime();
        String str=DateUtils.getTimeBeforeToDay(date);
        System.out.println(str+"-=----------------------------");


    }

    @Test
    public void test5(){

        Map<String,Object> map=new HashMap<>();


//        map.put("title","东单");
//        map.put("largeAreas","东单");
//        map.put("orientation","东");

        map.put("houseType","3");
        List<HouseDO> houseDOS = houseService.findByMore(map);
        for (HouseDO houseDO : houseDOS) {
            System.out.println(houseDO);
        }

    }
}