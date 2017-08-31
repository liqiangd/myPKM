package com.pkm.pkmDemo.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class SampleRedis {

	public static void main(String[] args) {
		         // 连接本地的 Redis 服务
		         Jedis jedis = new Jedis("192.168.190.156",6379);
		         System.out.println("连接本地的 Redis 服务成功！");
		         // 查看服务是否运行
		         System.out.println("服务 正在运行: " + jedis.ping());
		     }
		     @Test
		     //Redis Java String(字符串) 实例
		     public void TestRedisString(){
		         //连接本地的 Redis 服务
		           Jedis jedis = new Jedis("192.168.190.156",6379);
		           System.out.println("Connection to server sucessfully");
		           //设置 redis 字符串数据
		           jedis.set("runoobkey", "Redis tutorial");
		 //         // 获取存储的数据并输出
		          System.out.println("Stored string in redis:: "+ jedis.get("runoobkey"));
		          String name = jedis.get("theName");
		          System.out.println("Stored theName in redis:: "+ name);
		     }

}
