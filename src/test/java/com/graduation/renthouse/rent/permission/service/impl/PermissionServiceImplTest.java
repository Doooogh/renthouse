package com.graduation.renthouse.rent.permission.service.impl;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.permission.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceImplTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void recursion() {
        permissionService.recursion(1);
    }

    @Test
    public void tete(){
        for (Tree<PermissionDO> tree : permissionService.getTree().getChildren()) {
            System.out.println(tree.getText());
            for (Tree<PermissionDO> permissionDOTree : tree.getChildren()) {
                System.out.println(permissionDOTree.getText());
            }
        }

    }

    @Test
    public void test11(){
        int i=0;
        for (PermissionDO permissionDO : permissionService.list(new HashMap<>())) {
            System.out.println("---------------"+ i++);
            System.out.println(permissionDO.getName());
        }

    }

    @Test
    public void test34(){
//        Tree<PermissionDO> treeByUserId = permissionService.getTreeByUserId(28);
        Tree<PermissionDO> tree = permissionService.getTree(16);
        for (Tree<PermissionDO> permissionDOTree : tree.getChildren()) {
            if((boolean)(permissionDOTree.getState().get("selected"))){
                System.out.println(permissionDOTree.getText());
            }
            System.out.println("--------------------");
        }
    }
}