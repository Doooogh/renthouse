package com.graduation.renthouse.rent.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Tes {
    @GetMapping("/testtest")
    public  void test(){
        int a=1/0;
    }
}
