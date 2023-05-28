//package com.example.graduation.config;
//
//import io.lettuce.core.RedisClient;
//import io.lettuce.core.api.StatefulRedisConnection;
//import io.lettuce.core.api.sync.RedisCommands;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class LettuceConfig {
//
//    @Bean(destroyMethod = "shutdown")
//    RedisClient redisClient() {
//        return RedisClient.create("redis://123456@124.222.8.252:6379/0");
//    }
//
//    @Bean(destroyMethod = "close")
//    StatefulRedisConnection<String, String> connection(RedisClient redisClient) {
//        return redisClient.connect();
//    }
//
//    @Bean
//    RedisCommands<String, String> redis(StatefulRedisConnection<String, String> connection) {
//        return connection.sync();
//    }
//}
