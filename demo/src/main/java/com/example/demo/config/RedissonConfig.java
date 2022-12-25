package com.example.demo.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("redis://${redis.host:127.0.0.1}:${redis.port:6379}")
    private String address;
    @Value("${redis.database:0}")
    private Integer database;
    @Value("${redis.timeout:3000}")
    private Integer timeout;
    @Value("${redis.connection.poolSize:32}")
    private Integer connectionPoolSize;
    @Value("${redis.connection.minimumIdle-size:10}")
    private Integer connectionMiniIdleSize;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setDatabase(database)
                .setTimeout(timeout)
                .setConnectionPoolSize(connectionPoolSize)
                .setConnectionMinimumIdleSize(connectionMiniIdleSize);
        return Redisson.create(config);
    }
}
