package com.graduation.renthouse.system.aspect;


import com.graduation.renthouse.rent.user.dao.UserDao;
import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.system.annotation.Log;
import com.graduation.renthouse.system.log.domain.LogDO;
import com.graduation.renthouse.system.log.service.LogService;
import com.graduation.renthouse.system.utils.HttpContextUtils;
import com.graduation.renthouse.system.utils.IPUtils;
import com.graduation.renthouse.system.utils.JSONUtils;
import com.graduation.renthouse.system.utils.ShiroUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    LogService logService;

    @Autowired
    UserDao userDao;


    @Pointcut("@annotation(com.graduation.renthouse.system.annotation.Log)")
    public void logPointCut() {
    }




    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();

        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        saveLog(point,time);
        return result;
    }

    public void saveLog(ProceedingJoinPoint joinPoint,long time) throws InterruptedException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDO sysLog = new LogDO();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            // 注解上的描述
            sysLog.setOperation(syslog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONUtils.beanToJson(args);
            sysLog.setParams(params);
        } catch (Exception e) {

        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 用户名
        Object object=ShiroUtils.getSubjct().getPrincipal();
        UserDO currUser=userDao.getByUsername((String) object);
        if (null == currUser) {
            if (null != sysLog.getParams()) {
                sysLog.setUserId(-1L);
                sysLog.setUsername(sysLog.getParams());
            } else {
                sysLog.setUserId(-1L);
                sysLog.setUsername("获取用户信息为空");
            }
        } else {
            sysLog.setUserId((long)currUser.getUserId());
            sysLog.setUsername(currUser.getUsername());
        }
        sysLog.setTime((int) time);
        // 系统当前时间
        Date date = new Date();
        sysLog.setGmtCreate(date);
        // 保存系统日志
        logService.save(sysLog);

    }

}
