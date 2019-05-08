package com.graduation.renthouse.system.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author shangpeng
 * @version V1.0
 * @Date 2018/10/18 13:43
 */
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");

    private static  final  String pre="plateform";
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @param expire
     */
    public static void setExpir(final String key, final Object value, final long expire) {
        redisTemplate.opsForValue().set(pre+key, value,expire, TimeUnit.SECONDS);
    }
    public static void set(final String key, final Object value) {
        redisTemplate.opsForValue().set(pre+key, value);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(final String key, Class<T> clazz) {
        return (T) redisTemplate.boundValueOps(pre+key).get();
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public static Object getObj(final String key){
        return redisTemplate.boundValueOps(pre+key).get();
    }

    /**
     * 删除，根据key精确匹配
     *
     * @param key
     */
    public static void del(final String... key) {
        if(key!=null&&key.length>0){
            for (int i=0;i<key.length;i++){
                key[i]=pre+key[i];
            }
        }
        redisTemplate.delete(Arrays.asList(key));
    }

    /**
     * 批量删除，根据key模糊匹配
     *
     * @param pattern
     */
    public static void delpn(final String... pattern) {
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(pre+kp + "*"));
        }
    }

    /**
     * key是否存在
     *
     * @param key
     */
    public static boolean exists(final String key) {
        return redisTemplate.hasKey(pre+key);
    }

}
