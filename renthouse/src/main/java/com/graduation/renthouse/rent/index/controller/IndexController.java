package com.graduation.renthouse.rent.index.controller;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.permission.service.PermissionService;
import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.rent.user.service.UserService;
import com.graduation.renthouse.system.utils.BuildTree;
import com.graduation.renthouse.system.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller()
//@RequestMapping("/index")
public class IndexController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(Model model){
        UserDO user= (UserDO) ShiroUtils.getSubjct().getSession().getAttribute("user");
        String username=user.getUsername();
        Integer userId=user.getUserId();
        System.out.println("用户名为："+username+"-----ID为："+userId);
        Set<PermissionDO> permissionsSets = userService.getPermissionsByUserId(userId);
        List<Tree<PermissionDO>> trees = BuildTree.buildList(userService.getTree(permissionsSets),"0");
        model.addAttribute("trees",trees);
        model.addAttribute("username",username);
        return "/index";
    }


}
