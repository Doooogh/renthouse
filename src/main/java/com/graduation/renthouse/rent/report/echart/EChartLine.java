package com.graduation.renthouse.rent.report.echart;

import java.util.*;

/**
 * @author shangpeng
 * @version V1.0
 * 对报表的统一封装
 * @Date 2018/9/24 15:29
 */
public class EChartLine {

    private Set<String> type=new HashSet();//种类 无重复
    private List<String> x=new ArrayList();//x轴数据
    private Map<String,List<String>> y=new HashMap();//y轴数据  里面的list 顺序和xdata的顺序一致
    private List rows=new ArrayList();
    public Set<String> getType() {
        return type;
    }

    public void setType(Set<String> type) {
        this.type = type;
    }

    public List<String> getX() {
        return x;
    }

    public void setX(List<String> x) {
        this.x = x;
    }

    public Map<String, List<String>> getY() {
        return y;
    }

    public void setY(Map<String, List<String>> y) {
        this.y = y;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
