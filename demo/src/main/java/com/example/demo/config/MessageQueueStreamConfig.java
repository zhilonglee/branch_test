package com.example.demo.config;

import com.example.demo.component.MyPartitionKeyExtractor;
import com.example.demo.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.context.support.WebApplicationContextUtils;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author fanxiaoyu
 */
@Configuration
@Slf4j
public class MessageQueueStreamConfig {

    @Bean
    public Consumer<Person> sink() {
        return person -> {
            log.error("consumePerson Received: " + person);
        };
    }


    @Bean
    public Function<Person, Person> transfer() {
        return person -> {
            log.error("transfer Received: " + person);
            person.setName("caichao");
            return person;
        };
    }

    @Bean
    public Function<Tuple2<Flux<Person>, Flux<Person>>, Flux<Person>> gather() {
        return tuple -> {
            Flux<Person> t1 = tuple.getT1();
            Flux<Person> t2 = tuple.getT2();
            return Flux.merge(t1, t2);
        };
    }

    @Bean
    public AtomicInteger autoIndex() {
        return new AtomicInteger(0);
    }

}
