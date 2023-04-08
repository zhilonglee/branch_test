package com.example.demo.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
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
        Config config = getConfig();
        return Redisson.create(config);
    }

    @NotNull
    private Config getConfig() {
        Config config = new Config();
                // 监控锁的看门狗超时时间单位为毫秒。该参数只适用于分布式锁的加锁请求中未明确使用leaseTimeout参数的情况。
                // 如果该看门口未使用lockWatchdogTimeout去重新调整一个分布式锁的lockWatchdogTimeout超时，那么这个锁将变为失效状态。
                // 这个参数可以用来避免由Redisson客户端节点宕机或其他原因造成死锁的情况。
        config.setLockWatchdogTimeout(30000);
        config.useSingleServer()
                .setAddress(address)
                .setDatabase(database)
                .setTimeout(timeout)
                .setConnectionPoolSize(connectionPoolSize)
                .setConnectionMinimumIdleSize(connectionMiniIdleSize)
                .setRetryAttempts(3)
                .setRetryInterval(1000);
        return config;
    }
}
