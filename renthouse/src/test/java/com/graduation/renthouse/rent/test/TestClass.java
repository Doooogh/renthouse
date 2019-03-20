package com.graduation.renthouse.rent.test;

import com.alibaba.druid.pool.PreparedStatementPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {

    @Test
    public void test1(){
        String username="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/renthouse";
        String driver="com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(username, password, url);
            PreparedStatement ps=connection.prepareStatement("select *from order ");
            ResultSet resultSet=ps.executeQuery();
            long aLong = resultSet.getLong("createdate");
            System.out.println(aLong);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
