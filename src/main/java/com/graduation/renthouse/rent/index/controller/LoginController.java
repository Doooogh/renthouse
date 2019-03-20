package com.graduation.renthouse.rent.index.controller;

import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.rent.user.service.UserService;
import com.graduation.renthouse.system.utils.R;
import com.graduation.renthouse.system.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    Logger logger=LoggerFactory.getLogger(getClass());


    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String index(){
        return "/login/login";
    }

    @GetMapping("/403")
    public String login1(){
        return "/noPer";
    }


    //用户登录
    @PostMapping("/login")
    @ResponseBody
    public R login(String username,String password) {
        UserDO user=userService.getByUsername(username);
        Subject subject = SecurityUtils.getSubject();
        if(user!=null){
            subject.getSession().setAttribute("user",user);
        }else{
            return R.error(1,"账号不存在");
        }
        System.out.println(subject.getSession().getAttribute("user")+"---------------------------00000000000000");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);//会跳到我们自定义的realm中
        } catch (Exception e) {
            String exceptionClassName = e.getClass().getName();
            System.out.println(exceptionClassName+"-------------------------------");
            System.out.println(UnknownAccountException.class.getName()+"--------------------");
            if (exceptionClassName != null) {
                if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                    //最终会抛给异常处理器
                    return R.error(1,"账号不存在");
                } else if (IncorrectCredentialsException.class.getName().equals(
                        exceptionClassName)) {
                    return R.error(2,"用户名/密码错误");
                } else {
                    //最终在异常处理器生成未知错误.
                    return R.error(3,"其他异常信息");
                }
            }
            return  R.error(4,"没有权限");
        }
        return R.ok();




       /* //如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName= (String) request.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器

                model.addAttribute("errorMsg", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                model.addAttribute("errorMsg", "用户名/密码错误");
            } else {
                //最终在异常处理器生成未知错误.
                model.addAttribute("errorMsg", "其他异常信息");
            }
        }
        //此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
        //登陆失败还到login页面
        return "forward:/login.jsp";*/
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/";
    }


}
