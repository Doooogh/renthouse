package com.graduation.renthouse.rent.report.domain;

import com.graduation.renthouse.rent.report.help.Chart;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
public class WelcomeReport {

    //类别名称
    @Chart(type = 1)
    private String type;

    //发送量
    @Chart(Y = 1)
    private Integer num;

    //发送时间
    @Chart(X = true)
    private String date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WelcomeReport{" +
                "type='" + type + '\'' +
                ", num=" + num +
                ", date='" + date + '\'' +
                '}';
    }
}
