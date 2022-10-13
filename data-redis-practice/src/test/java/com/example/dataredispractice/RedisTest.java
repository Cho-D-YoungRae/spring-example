package com.example.dataredispractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void practce() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Long incrResult = ops.increment("post:1:comments:count");
        System.out.println("incrResult = " + incrResult);
    }
}
