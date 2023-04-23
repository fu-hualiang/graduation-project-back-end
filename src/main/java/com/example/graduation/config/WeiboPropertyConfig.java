package com.example.graduation.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeiboPropertyConfig {

    @Data
    public static class WeiboProperty {
        @Value("${weibo.clientId}")
        String clientId;
        @Value("${weibo.clientSecret}")
        String clientSecret;
        @Value("${weibo.redirectUri}")
        String redirectUri;
        @Value("${weibo.grantType}")
        String grantType;
        @Value("${weibo.appKey}")
        String appKey;
    }

    @Bean
    WeiboProperty weiboProperty() {
        return new WeiboProperty();
    }
}
