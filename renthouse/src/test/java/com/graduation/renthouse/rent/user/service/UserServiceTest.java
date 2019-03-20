package com.graduation.renthouse.rent.user.service;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.system.utils.BuildTree;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    String username="testman";
    @Test
    public void getByUsername() {
        UserDO user = userService.getByUsername(this.username);
        System.out.println(user);
    }

    @Test
    public void getRoles() {
        Set<String> roles = userService.getRoles(username);
        for (String role : roles) {
            System.out.println("角色名称："+role);
        }
    }

    @Test
    public void getPermissions() {
        Set<String> permissions = userService.getPermissions(username);
        for (String permission : permissions) {
            if(!StringUtils.isNotBlank(permission)){
                System.out.println(permission+"1321321313");
            }
            System.out.println("权限为："+permission);
        }
    }

    @Test
    public  void tetet(){

        Set<PermissionDO> sets = userService.getPermissionsByUserId(29);
        List<Tree<PermissionDO>> trees = BuildTree.buildList(userService.getTree(sets), "0");
        for (Tree<PermissionDO> tree : trees) {
            System.out.println(tree.getText());
        }
    }
}