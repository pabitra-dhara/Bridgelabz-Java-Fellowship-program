package com.fundoonotesapp.fundoo.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    private final StringRedisTemplate redisTemplate;

    public RedisController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/redis-test")
    public String redisTest() {

        redisTemplate.opsForValue().set("name", "Pabitra");

        return redisTemplate.opsForValue().get("name");
    }
}
