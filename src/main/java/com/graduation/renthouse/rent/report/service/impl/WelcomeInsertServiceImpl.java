package com.graduation.renthouse.rent.report.service.impl;

import com.graduation.renthouse.rent.insertlog.dao.InsertLogExDao;
import com.graduation.renthouse.rent.report.domain.WelcomeReport;
import com.graduation.renthouse.rent.report.echart.EChartLine;
import com.graduation.renthouse.rent.report.help.ChartUtils;
import com.graduation.renthouse.rent.report.service.WelcomeInsertService;
import com.graduation.renthouse.system.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WelcomeInsertServiceImpl implements WelcomeInsertService {

    @Autowired
    private InsertLogExDao insertLogExDao;

    @Override
    public EChartLine welcomeList(Map<String, Object> map) {
        if(map==null){
            map=new HashMap<>();
        }
        if(map.get("rangeDate")!=null&&StringUtils.isNotBlank((CharSequence) map.get("rangeDate"))){
           Map<String,String> date =format((String) map.get("rangeDate"));
           map.put("start",date.get("start"));
           map.put("end",date.get("end"));
        }else{
           map.put("start",DateUtils.getMonthFirstDate());
           map.put("end",DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        }
        List<WelcomeReport> report = insertLogExDao.report(map);
        return ChartUtils.adpter(report,WelcomeReport.class,1,1);
    }

    public Map<String,String> format(String dataRange){
        Map<String,String>map=new HashMap<>();
        if(org.apache.commons.lang3.StringUtils.isNotBlank(dataRange)){
            String startTime=dataRange.substring(0,10);
            String end=dataRange.substring(13);
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
            Date date=null;
            try {
                date=sd.parse(end);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c=Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, 1);
            Date endDate=c.getTime();
            String endTime=sd.format(endDate);

            map.put("start",startTime);
            map.put("end",endTime);
        }
        return map;
    }

}
