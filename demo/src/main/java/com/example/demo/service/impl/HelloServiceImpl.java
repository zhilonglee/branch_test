package com.example.demo.service.impl;

import com.example.demo.service.HelloService;

/**
 * @author fanxiaoyu
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return String.format("hello %s !!!",  name);
    }
}
