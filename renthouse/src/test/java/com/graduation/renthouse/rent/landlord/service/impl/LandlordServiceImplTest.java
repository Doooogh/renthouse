package com.graduation.renthouse.rent.landlord.service.impl;

import com.graduation.renthouse.rent.house.domain.HouseDO;
import com.graduation.renthouse.rent.landlord.dao.LandlordDao;
import com.graduation.renthouse.rent.landlord.domain.LandlordDO;
import com.graduation.renthouse.rent.landlord.domain.LandlordVO;
import com.graduation.renthouse.rent.landlord.service.LandlordService;
import com.graduation.renthouse.rent.order.domain.OrderDO;
import com.graduation.renthouse.rent.order.service.OrderService;
import com.graduation.renthouse.system.utils.PageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LandlordServiceImplTest {

    @Autowired
    private LandlordService landlordService;

    @Autowired
    private OrderService orderService;


    @Test
    public void test6(){
        System.out.println(UUID.randomUUID().toString());
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String paymentID =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 订单ID
        System.out.println(paymentID);
    }

    @Test
    public void get() {
        List<LandlordDO> list = landlordService.list(new HashMap<>());
        for (LandlordDO landlordDO : list) {
            System.out.println(landlordDO);
        }
    }

    @Test
    public void list() {
        Map<String,Object> map=new HashMap<>();
        map.put("offset",0);
        map.put("limit",1);
        PageUtils pageUtils = landlordService.listByLandlord(map);
        List<?> rows = pageUtils.getRows();
        System.out.println(pageUtils.getTotal()+"---------------------------");
        for (Object row : rows) {
            LandlordVO landlordVO=(LandlordVO) row;
            for (HouseDO houseDO : landlordVO.getHouses()) {
                System.out.println(houseDO.getTitle());
            }

        }

    }

    @Test
    public void test5(){
        List<OrderDO> list = orderService.list(new HashMap<String, Object>());
        System.out.println(list.get(0).getCreatedate());
        System.out.println(list.get(0).getUpdatedate());
    }
}