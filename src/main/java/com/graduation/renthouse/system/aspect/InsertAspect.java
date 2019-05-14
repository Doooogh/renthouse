package com.graduation.renthouse.system.aspect;

import com.graduation.renthouse.rent.insertlog.dao.InsertLogDao;
import com.graduation.renthouse.rent.insertlog.domain.InsertLogDO;
import com.graduation.renthouse.rent.insertlog.service.InsertLogService;
import com.graduation.renthouse.rent.user.dao.UserDao;
import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.system.annotation.InsertLog;
import com.graduation.renthouse.system.annotation.Log;
import com.graduation.renthouse.system.log.domain.LogDO;
import com.graduation.renthouse.system.log.service.LogService;
import com.graduation.renthouse.system.utils.HttpContextUtils;
import com.graduation.renthouse.system.utils.IPUtils;
import com.graduation.renthouse.system.utils.JSONUtils;
import com.graduation.renthouse.system.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class InsertAspect {

    @Autowired
    private LogService logService;

    @Autowired
    private InsertLogService insertLogService;

    @Autowired
    private UserDao userDao;



    @Pointcut("@annotation(com.graduation.renthouse.system.annotation.InsertLog)")
    public void InsertLogPointCut() {
    }

    @Before("InsertLogPointCut()")
    public void test1(){
        System.out.println("前置通知");
    }
    @AfterReturning("InsertLogPointCut()")
    public void after(JoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveInsertLog(point, time);
    }

    void saveInsertLog(JoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDO sysLog = new LogDO();
        InsertLogDO insertLogDO=new InsertLogDO();
        InsertLog insertLog = method.getAnnotation(InsertLog.class);
        if (insertLog != null) {
            // 注解上的描述
//            String insertAnno=insertLogDO.setType(insertLog.value());

            String insertAnno=insertLog.value();//获取注解上描述

            String insertType="";

            if(insertAnno.equals("landlord")){
                insertType="添加房东";
            }else if(insertAnno.equals("tenant")){
                insertType="添加租客";
            }else if(insertAnno.equals("house")){
                insertType="添加房子";
            }else if(insertAnno.equals("order")){
                insertType="添加订单";
            }
            insertLogDO.setType(insertAnno);
            insertLogDO.setInsertType(insertType);
        }
        Object object=ShiroUtils.getSubjct().getPrincipal();
        UserDO currUser=userDao.getByUsername((String) object);
        insertLogDO.setCreateUid(currUser.getUserId());
        insertLogDO.setCreateTime(new Date());
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        try{
            insertLogService.save(insertLogDO);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
