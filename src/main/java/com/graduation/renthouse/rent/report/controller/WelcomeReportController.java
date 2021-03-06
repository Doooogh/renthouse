package com.graduation.renthouse.rent.report.controller;

import com.graduation.renthouse.rent.report.echart.EChartLine;
import com.graduation.renthouse.rent.report.service.WelcomeInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/welcome/report")
public class WelcomeReportController {

    @Autowired
    private WelcomeInsertService welcomeInsertService;


    @GetMapping("/welcomeReport")
    @ResponseBody
    public EChartLine welcomeReport(String rangeDate){
        Map<String,Object> map=new HashMap<>();
        map.put("rangeDate",rangeDate);
        return welcomeInsertService.welcomeList(map);
    }

}
