package com.graduation.renthouse.rent.role.service.impl;

import com.graduation.renthouse.rent.role.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void getTime() throws InterruptedException {
        String first =  roleService.getTime("param");
        System.out.println("第一次调用："+first);
        Thread.sleep(5000);
        String second =   roleService.getTime("param");
        System.out.println("第二次调用："+second);
        Thread.sleep(30000);
        String third =   roleService.getTime("param");
        System.out.println("第三次调用："+third);

    }
}