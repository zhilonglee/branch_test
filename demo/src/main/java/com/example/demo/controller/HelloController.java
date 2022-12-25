package com.example.demo.controller;

import com.example.demo.component.MyPartitionKeyExtractor;
import com.example.demo.pojo.Person;
import com.example.demo.service.HelloService;
import com.example.demo.service.impl.HelloServiceImpl;
import com.example.demo.utils.LocalRegister;
import com.example.demo.utils.ProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fanxiaoyu
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private AtomicInteger autoIndex;

    @Autowired
    private RedissonClient redissonClient;


    @RequestMapping("/logback/test")
    public ResponseEntity sayHello (@RequestParam(defaultValue = "caichao") String name){
        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warn message");
        log.error("This is an error message");
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        return ResponseEntity.ok(helloService.sayHello(name));
    }

    @PostMapping("/getHeader")
    public Map getHeader(@RequestHeader("token") String accessToken, String id) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("access_token", accessToken);
        params.put("id", id);
        return params;
    }

    @RequestMapping("/stream/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delegateToSupplier(@RequestParam(defaultValue = "caichao") String name) {
        Person person = new Person();
        person.setName(name);
        MessageBuilder<Person> messageBuilder = MessageBuilder.withPayload(person).setHeader(MyPartitionKeyExtractor.PARTITION_PROP, autoIndex.getAndIncrement());

        streamBridge.send("output", messageBuilder.build());

    }


    @RequestMapping("/stream/send-gather")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delegateToSupplier(@RequestParam(defaultValue = "caichao") String msg, @RequestParam(defaultValue = "dashabi") String msg2) {
        Person person = new Person();
        person.setName(msg);
        MessageBuilder<Person> messageBuilder = MessageBuilder.withPayload(person).setHeader(MyPartitionKeyExtractor.PARTITION_PROP, autoIndex.getAndIncrement());
        streamBridge.send("gather1", messageBuilder.build());
        Person person2 = new Person();
        person.setName(msg2);
        MessageBuilder<Person> messageBuilder2 = MessageBuilder.withPayload(person).setHeader(MyPartitionKeyExtractor.PARTITION_PROP, autoIndex.getAndIncrement());

        streamBridge.send("gather2",  messageBuilder2.build());
    }

    @RequestMapping("/redisson")
    public ResponseEntity redissonTest() {
        RBucket<Object> bucket = redissonClient.getBucket("666");
        bucket.set("caichao",3, TimeUnit.MINUTES);
        Object o = bucket.get();
        log.info(o.toString());
        return ResponseEntity.ok("666");
    }
}
