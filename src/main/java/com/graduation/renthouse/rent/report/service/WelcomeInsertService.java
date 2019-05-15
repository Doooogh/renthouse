package com.graduation.renthouse.rent.report.service;

import com.graduation.renthouse.rent.report.echart.EChartLine;

import java.util.Map;

public interface WelcomeInsertService {

    EChartLine welcomeList(Map<String, Object> map);
}
