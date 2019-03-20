package com.graduation.renthouse.rent.user.service.impl;

import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {


    @Autowired
    private UserService userService;

    @Test
    public void getPermissionsByUserId() {
        for (PermissionDO permissionDO : userService.getPermissionsByUserId(29)) {
            System.out.println(permissionDO.getName());
        }
    }
}