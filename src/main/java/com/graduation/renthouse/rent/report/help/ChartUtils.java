package com.graduation.renthouse.rent.report.help;/**
 package cn.ultradata.echart;/**
 * Created by peng on 2018/1/18.
 */

import com.google.common.collect.Lists;
import com.graduation.renthouse.rent.report.echart.EChartLine;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shangpeng
 * @version V1.0
 * @Description: TODO
 * @Date 2018/1/18
 * 分解list 实体类
 * 使其适配于固定的格式
 * 用于 模板方法 装饰模板
 */
public class ChartUtils {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param s   查询出来的数据
     * @param cls 映射雷诺
     * @return
     */
    public static EChartLine adpter(List s, Class<?> cls, int type, int y) {
        EChartLine eChart = new EChartLine();

        //定义  列
        if (s != null && s.size() > 0) {

            eChart.setRows(s);
            Field tF = null;
            Field xF = null;
            Field yF = null;
            //装注解信息
            List<Object[]> annotationList = Lists.newArrayList();
            Field[] fs = cls.getDeclaredFields();
            for (Field f : fs) {
                Chart ef = f.getAnnotation(Chart.class);
                if (ef != null) {
                    if (ef.type() == type) {
                        tF = f;
                    }
                    if (ef.X()) {
                        xF = f;
                    }
                    if (ef.Y() == y) {
                        yF = f;
                    }
                }
            }

            if (xF == null || yF == null || tF == null)
                return eChart;

            Object tTemp;
            String tString = null;
            Object xTemp;
            String xString = null;
            Object yTemp;
            String yString = null;
            int yType=0;
            Map<String, Map<String, String>> temp = new HashMap<String, Map<String, String>>();
            for (Object o : s) {
                tTemp = Reflections.invokeGetter(o, tF.getName());
                if (tTemp != null) {
                    if (tTemp instanceof Date) {
                        tString = df.format((Date) tTemp);
                    } else {
                        tString = tTemp.toString();
                    }
                    eChart.getType().add(tString);
                }

                xTemp = Reflections.invokeGetter(o, xF.getName());
                if (xTemp != null) {
                    if (xTemp instanceof Date) {
                        xString = df.format((Date) xTemp);

                    } else {
                        xString = xTemp.toString();
                    }

                    if (!eChart.getX().contains(xString)) {
                        eChart.getX().add(xString);
                    }
                }
                yTemp = Reflections.invokeGetter(o, yF.getName());
                if (yTemp != null) {
                    yString = yTemp.toString();
                    if(yTemp instanceof Double){
                        yType=1;
                    }
                }

                if (temp.containsKey(tString)) {
                    temp.get(tString).put(xString, yString);
                } else {
                    Map<String, String> m = new HashMap();
                    m.put(xString, yString);
                    temp.put(tString, m);
                }

            }


            //x轴排序
            Collections.sort(eChart.getX());

            //封装y轴

            for (String tType : eChart.getType()) {
                eChart.getY().put(tType, Lists.newArrayList());
                for (int i = 0; i < eChart.getX().size(); i++) {
                    String t = temp.get(tType).get(eChart.getX().get(i));
                    if(yType==0){
                        if (t == null) t = "0";
                        eChart.getY().get(tType).add(i, Integer.valueOf(t).toString());
                    }else {
                        if (t == null) t = "0.00";
                        eChart.getY().get(tType).add(i, Double.valueOf(t).toString());
                    }

                }
            }

        }

        return eChart;
    }

}
