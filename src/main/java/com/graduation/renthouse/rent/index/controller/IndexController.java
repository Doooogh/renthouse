package com.graduation.renthouse.rent.index.controller;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.rent.house.service.HouseService;
import com.graduation.renthouse.rent.index.entity.Info;
import com.graduation.renthouse.rent.landlord.service.LandlordService;
import com.graduation.renthouse.rent.order.service.OrderService;
import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.permission.service.PermissionService;
import com.graduation.renthouse.rent.tenant.service.TenantService;
import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.rent.user.service.UserService;
import com.graduation.renthouse.system.utils.BuildTree;
import com.graduation.renthouse.system.utils.ShiroUtils;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller()
//@RequestMapping("/index")
public class IndexController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;
    @Autowired
    private LandlordService landlordService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private OrderService orderService;

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
        return "/index2";
    }

    @GetMapping("/welcome")
    public String welcome(Model model){
        UserDO user= (UserDO) ShiroUtils.getSubjct().getSession().getAttribute("user");
        String username=user.getUsername();
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        SimpleDateFormat sd=new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        String currentTime=sd.format(date);
        Map <String,Object> map=new HashMap<>();
        int userNum = userService.count(map);
        int landlordNum = landlordService.count(map);
        int tenantNum = tenantService.count(map);
        int houseNum = houseService.count(map);
        int orderNum=orderService.count(map);
        List<Info> list=new ArrayList<>();

        Info info1=new Info("用户数",userNum);
        Info info2=new Info("房东数",landlordNum);
        Info info3=new Info("租客数",tenantNum);
        Info info4=new Info("房子数",houseNum);
        Info info5=new Info("订单数",orderNum);
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        list.add(info5);


        model.addAttribute("username",username);
        model.addAttribute("infos",list);
        model.addAttribute("currentTime",currentTime);
        return "welcome";
    }


}
