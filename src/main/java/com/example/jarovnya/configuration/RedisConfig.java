package com.example.jarovnya.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class RedisConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("localhost", 6379);
        configuration.setPassword(RedisPassword.of("1234"));
        configuration.setDatabase(1);
        return new LettuceConnectionFactory(configuration);
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactoryFroBytes() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("localhost", 6379);
        configuration.setPassword(RedisPassword.of("1234"));
        configuration.setDatabase(0);
        return new LettuceConnectionFactory(configuration);
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplateForBytes() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactoryFroBytes());
        return template;
    }
}
