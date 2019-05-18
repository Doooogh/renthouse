package com.graduation.renthouse.system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class TaskAspect {

    private static final Logger logger = LoggerFactory.getLogger(TaskAspect.class);


    @Pointcut("execution( * com.graduation.renthouse.system.job.task.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void taskLogPointCut() {
    }


    @After("taskLogPointCut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        logger.info("定时方法为："+signature.getName());
        logger.info("定时任务结束");



    }




}
