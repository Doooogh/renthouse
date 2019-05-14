package com.graduation.renthouse.rent.insertlog.dao;

import com.graduation.renthouse.rent.report.domain.WelcomeReport;

import java.util.List;
import java.util.Map;

public interface InsertLogExDao {

    List<WelcomeReport> report(Map<String,Object> map);

}
