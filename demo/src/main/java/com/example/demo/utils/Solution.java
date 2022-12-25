package com.example.demo.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "aaryanna",
                "aayanna",
                "annebelle"
        );

        names.stream()
                .filter(Fliter.nameStartingWithPrefix(scanner.nextLine()))
                .map(Mapper.getDistinctCharactersCount())
                .forEachOrdered(System.out::println);


    }
}


/**
 * 转换对象
 */
class  CharactersCount {
    private final String name;
    private final Integer distinctCharacterCount;

    public CharactersCount(String name, Integer distinctCharacterCount) {
        this.name = name;
        this.distinctCharacterCount = distinctCharacterCount;
    }

    @Override
    public String toString() {
        return "\"" + this.name + "\" has " + this.distinctCharacterCount + " distinct characters";
    }
}

class Fliter {
    public static Predicate<String> nameStartingWithPrefix(String prefix) {
        return (str) -> str.startsWith(prefix);
    }
}

class Mapper {
    public static Function<String, CharactersCount> getDistinctCharactersCount() {
        return (str) -> {
            long count = Arrays.stream(str.split("")).distinct().count();
            return new CharactersCount(str, Math.toIntExact(count));
        };
    }
}