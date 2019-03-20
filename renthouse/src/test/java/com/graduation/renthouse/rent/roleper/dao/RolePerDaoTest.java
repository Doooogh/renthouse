package com.graduation.renthouse.rent.roleper.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RolePerDaoTest {

    @Autowired
    private RolePerDao rolePerDao;

    @Test
    public void selectPerIdByRoleIds() {
        List<Integer> roles=new ArrayList<>();
        roles.add(16);
        for (Integer integer : rolePerDao.selectPerIdByRoleIds(roles)) {
            System.out.println(integer);
        }
    }
}