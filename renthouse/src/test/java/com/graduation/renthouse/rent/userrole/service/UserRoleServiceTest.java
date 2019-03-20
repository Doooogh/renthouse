package com.graduation.renthouse.rent.userrole.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void selectRoleIdByUserId() {
        for (Integer integer : userRoleService.selectRoleIdByUserId(29)) {
            System.out.println(integer);
        }
    }
}