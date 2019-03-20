package com.graduation.renthouse.system.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtils {


    static  String str="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    public static String random(int length){

        String number="";
        for (int i=0;i<length;i++){
            Random r=new Random(str.length());
            number+=str.charAt(r.nextInt());
        }
        return number;
    }

    public static String numID(){
        String[] strings = UUID.randomUUID().toString().split("-");
        String numId="";
        for (String string : strings) {
            numId+=string;
        }
        return numId;
    }
}
