package com.example.demo.utils;

import com.example.demo.service.HelloService;
import com.example.demo.service.impl.HelloServiceImpl;

import java.lang.reflect.*;

/**
 * @author fanxiaoyu
 */
public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {
        Object o = Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{interfaceClass}, (proxy, method, args) -> {
            Class implClass = LocalRegister.fetch(interfaceClass.getName());
            Constructor constructor = implClass.getConstructor();
            Object implInstance = constructor.newInstance();
            return method.invoke(implInstance, args);
        });
        return (T) o;
    }

    public static void main(String[] args) {
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String caichao = helloService.sayHello("caichao");
        System.out.println(caichao);
    }
}
