package com.graduation.renthouse.system.utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class RedisPoolUtil {

    /**
     * 有序集合添加一个或者多个成员或者更新已经存在的成员的分数
     * @param key
     * @param map
     * @return  返回值代表每次放入redis的成员个数
     */
    public static  Long zadd(String key,Map<String,Double> map){
        Jedis jedis=null;
        Long result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.zadd(key, map);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }

    /**
     * 向有序集合添加一个成员，或者更新已存在成员的分数
     * @param key
     * @param score
     * @param member
     * @return
     */
    public static  Long zadd(String key,Double score,String member){
        Jedis jedis=null;
        Long result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.zadd(key,score,member);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }
    /**
     * 按权重排序后读取索引范围内元素及权重
     * @param key
     * @return
     */
    public static  Set<Tuple> zrangeWithScores(String key){
        Jedis jedis=null;
        Set<Tuple> result=new HashSet<>();
        try{
            jedis=RedisPool.getJedis();
            result=jedis.zrangeWithScores(key,0,-1);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }

    /**
     * 更新某个成员的分数（也就是绑定的次数）
     * @param key
     * @param increment   增或者减的量  一般曾传 1d   减传 -1d
     * @param member      每次取到的手机号
     * @return
     */
    public static  Double ZINCRBY(String key,Double increment,String member){
        Jedis jedis=null;
        Double result=null;

        try{
            jedis=RedisPool.getJedis();
            Double re=RedisPoolUtil.zscore(key,member);
            if(re<0||  (re!=0.0 && re-Math.abs(increment)<0) || (re==0.0 && re-increment>0)){
                increment=0d;
                result= jedis.zincrby(key,increment,member);
            }else {
                result=jedis.zincrby(key,increment,member);
            }
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }


    public static  Double zscore(String key,String member){
        Jedis jedis=null;
        Double result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.zscore(key,member);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }

    /**
     *
     * 对hash结构的操作封装---Hset
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static  Long Hset(String key,String field,String value){
        Jedis jedis=null;
        Long result=null;
        try{
            jedis=RedisPool.getJedis();
            jedis.select(1);
            result=jedis.hset(key,field,value);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * @param key
     * @param key
     * @param map
     * @return
     */
    public static  String  Hmset(String key,Map<String,String> map){
        Jedis jedis=null;
        String  result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.hmset(key,map);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }
    /**
     * 获取指定key的字段和值
     * hash 的HgetAll命令
     * @param key
     * @return
     */
    public static  Map<String,String>  HgetAllKey(String key){
        Jedis jedis=null;
        Map<String,String> result=null;
        try{
            jedis=RedisPool.getJedis();
            jedis.select(1);
            result=jedis.hgetAll(key);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }

    /**
     * 通过key 和字段 获取值
     * @param key
     * @param field
     * @return
     */
    public static  String  HgetKey(String key,String field){
        Jedis jedis=null;
        String  result=null;
        try{
            jedis=RedisPool.getJedis();
            jedis.select(1);
            result=jedis.hget(key,field);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return  result;
    }

    /**
     * String类型的key 设置有效期
     * @param key
     * @param value
     * @param exTime
     * @return
     */
    public static  String setEx(String key,String value,int exTime){
        Jedis jedis=null;
        String result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.setex(key,exTime,value);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 更新key的有效期
     * @param key
     * @param exTime
     * @return
     */
    public static  long expire(String key,int exTime){
        Jedis jedis=null;
        Long result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.expire(key,exTime);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * set
     * @param key
     * @param value
     * @return
     */
    public static void set(String key,String value){
        Jedis jedis=null;
        String result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.set(key,value);
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
        }
        RedisPool.returnResource(jedis);
    }

    public static String get(String key){
        Jedis jedis=null;
        String result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.get(key);
            return result;
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static Long expireTime(String key,int i){
        Jedis jedis=null;
        Long result=null;
        try{
            jedis=RedisPool.getJedis();
            result=jedis.expire(key,i);
            return result;
        }catch (Exception e){
            RedisPool.returnBrokenResource(jedis);
        }
        RedisPool.returnResource(jedis);
        return result;
    }

}
