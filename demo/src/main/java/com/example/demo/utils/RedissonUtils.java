package com.example.demo.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RFuture;
import org.redisson.api.RKeys;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

import java.util.Objects;

@Slf4j
public class RedissonUtils {

    private static RedissonClient redissonClient;

    private synchronized static void loadRedissonClient() {
        if (Objects.isNull(redissonClient)) {
            redissonClient = (RedissonClient) SpringContextUtil.getBean("redissonClient");
        }
    }

    public static void testAtomicLong() {
        loadRedissonClient();
        RAtomicLong longObject = redissonClient.getAtomicLong("myLong");
        longObject.set(3);
        // 同步执行方式
        longObject.compareAndSet(3, 401);
        // 异步执行方式
        RFuture<Boolean> result = longObject.compareAndSetAsync(401, 402);
        RFuture<Boolean> result2 = longObject.compareAndSetAsync(402, 403);
        RFuture<Boolean> result3 = longObject.compareAndSetAsync(403, 404);
    }

    public static void testKeys() {
        loadRedissonClient();
        RKeys keys = redissonClient.getKeys();

        Iterable<String> allKeys = keys.getKeys();
        Iterable<String> foundedKeys = keys.getKeysByPattern("myLong*");
        long numOfDeletedKeys = keys.delete("obj1", "obj2", "obj3");
        long deletedKeysAmount = keys.deleteByPattern("test?");
        String randomKey = keys.randomKey();
        long keysAmount = keys.count();
    }

    public static void subscribe(String topicName) {
        loadRedissonClient();
        RTopic topic = redissonClient.getTopic(topicName);
        topic.addListener(String.class, (channel, message) -> {
            log.info(message);
        });
    }

    public static void publish(String topicName, String msg) {
        loadRedissonClient();
        RTopic topic = redissonClient.getTopic(topicName);
        topic.publish(msg);
    }

    @SneakyThrows
    private RedissonUtils() {
        throw new IllegalAccessException("Access Deny");
    }
}
