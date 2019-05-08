package com.graduation.renthouse.system.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private  static JedisPool pool;//jedis连接池
    private  static Integer   maxTotal=Integer.parseInt(PropertiesUtil.getProperty("redis.max.total","20"));//最大连接数
    private  static Integer   maxIdle=Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle","10"));//最大空弦数，最大的空闲的jedis实例的个数
    private  static Integer   minIdle=Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle","2"));//最小空弦数，最小的空闲的jedis实例的个数
    private  static Integer   redisPort=Integer.parseInt(PropertiesUtil.getProperty("redis.port","6379"));//redis的端口
    private  static String    redisIp=PropertiesUtil.getProperty("redis.ip");//redis客户端的IP
    private  static boolean   redisOnBorrow=Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));//在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。
    private  static boolean   redisOnReturn=Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return","true"));


    private static void initPool(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxTotal(maxTotal);
        config.setTestOnBorrow(redisOnBorrow);
        config.setTestOnReturn(redisOnReturn);
        config.setBlockWhenExhausted(true);//连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
        pool=new JedisPool(config,redisIp,redisPort,1000*2);
    }
    static {
        initPool();
    }
    public static Jedis getJedis(){
        Jedis jedis=  pool.getResource();
        return jedis;
    }

    public static  void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }

    public static  void returnResource(Jedis jedis){
        pool.returnResource(jedis);
    }
}

