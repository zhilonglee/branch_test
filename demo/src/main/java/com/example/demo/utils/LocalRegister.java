package com.example.demo.utils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fanxiaoyu
 */
public class LocalRegister {
    private static ConcurrentHashMap<String, Class> map = new ConcurrentHashMap<>(16);

    public static void register(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class fetch(String interfaceName) {
        return map.get(interfaceName);
    }
}
