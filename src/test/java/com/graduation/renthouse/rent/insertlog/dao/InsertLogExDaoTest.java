package com.graduation.renthouse.rent.insertlog.dao;

import com.graduation.renthouse.rent.report.domain.WelcomeReport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertLogExDaoTest {

    @Autowired
    private InsertLogExDao insertLogExDao;

    @Test
    public void test(){
        List<WelcomeReport> report = insertLogExDao.report(new HashMap<>());
        for (WelcomeReport welcomeReport : report) {
            System.out.println(welcomeReport);
        }
    }

}