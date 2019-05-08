package com.graduation.renthouse.rent.report.help;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by
 * shangpeng
 * on 2018/09/24.
 * 统一化 报表 使其适应于模板
 * 满足适配器设计模式
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Chart {

    /**
     * 若data是对象要求实现comparable接口
     * @return
     */
    int type() default -1;

    /**
     * 若x轴是对象要求实现comparable接口
     * @return
     */
    boolean X() default false;


    int Y() default -1;

}
