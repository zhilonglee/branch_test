package com.example.demo.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 函数式工具类
 */
public class FunctionalUtils {
    private static Integer count = 0;
    public static Function<Integer, String> countPlusAndConvert2Str() {
        return (count) -> String.format("输出结果为：%d",++count);
    }

    public static Supplier<String> countPlusAndConvert2StrSupplier() {
        return () -> String.format("输出结果为：%d");
    }

    public static <R> R map(Function<Integer, ? extends R> mapper) {
        return mapper.apply(FunctionalUtils.count);
    }

    public static void main(String[] args) {
        System.out.println(map(countPlusAndConvert2Str()));
        System.out.println(map(countPlusAndConvert2Str()));
        System.out.println(map(countPlusAndConvert2Str()));
        System.out.println(map(countPlusAndConvert2Str()));
    }
}
