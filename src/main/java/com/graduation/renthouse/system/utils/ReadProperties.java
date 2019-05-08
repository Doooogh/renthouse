package com.graduation.renthouse.system.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Properties;

/**
 * @Auther: Administrator
 * @Date: 2018/9/12 14:56
 * @Description:
 */
public class ReadProperties {

    public static String getValue(String filePath,String key){
        Resource resource = new ClassPathResource(filePath);
        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            pps.load(in);
            String value = pps.getProperty(key);
            return value;
        }catch (IOException e) {
            e.printStackTrace();
             return null;
        }
    }

    public static  String getSValue(String key){
        return getValue("status.properties", key);
    }
}
