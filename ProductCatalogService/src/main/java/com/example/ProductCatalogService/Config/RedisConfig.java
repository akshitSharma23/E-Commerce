package com.example.ProductCatalogService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Long, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Long, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
//        template.setKeySerializer(new String  RedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}