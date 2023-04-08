package com.example.demo.utils;


import feign.Feign;
import feign.Logger;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class FeignFactoryUtil {

    public static <T> T getFeignClient(Class<T> clzz, String host) {
        return Feign.builder().client(new ApacheHttpClient())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(template -> template.header("Accept-Encoding", "gzip"))
                .target(clzz, host);
    }
}
