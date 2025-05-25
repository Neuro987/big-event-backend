package com.learn.bigevent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest // 添加这个注解，那么在单元测试执行之前会先初始化Spring容器
public class RedisTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet() {
        // 向redis中存入键值对
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("username", "vhsj");
        operations.set("id","1",15, TimeUnit.SECONDS);
        // System.out.println(operations.get("username"));
    }

    @Test
    public void testGet() {
        // 从redis中获取键值对
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        System.out.println(operations.get("username"));
    }
}
