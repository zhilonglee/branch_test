package com.example.demo.schedule;

import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author fanxiaoyu
 */
@Slf4j
@Component
public class ScheduleTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void printTime() throws InterruptedException {
        TimeUnit.SECONDS.sleep(6);
      log.warn("The present is : {}", LocalDateTime.now());
    }

    @Scheduled(cron = "00 * * * * ?")
    public void sayHello() throws InterruptedException {
        log.warn("hello... the time is: {}", LocalDateTime.now());
    }
}
