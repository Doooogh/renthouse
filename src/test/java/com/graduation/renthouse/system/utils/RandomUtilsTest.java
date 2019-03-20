package com.graduation.renthouse.system.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomUtilsTest {

    @Test
    public void random() {
//        System.out.println(RandomUtils.random(4));
        System.out.println(RandomUtils.numID());
    }

    @Test
    public void numID() {
    }
}