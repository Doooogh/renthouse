package com.graduation.renthouse.rent.permission.dao;

import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.userrole.dao.UserRoleDao;
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
public class PermissionDaoTest {


    @Autowired
    PermissionDao permissionDao;

    @Test
    public void selectByRoles() {
        List<Integer> roles=new ArrayList<>();
        roles.add(16);
        List<PermissionDO> dos = permissionDao.selectByRoles(roles);
        for (PermissionDO aDo : dos) {
            System.out.println(aDo.getName());
        }
    }
}