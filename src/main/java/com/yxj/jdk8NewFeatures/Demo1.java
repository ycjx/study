package com.yxj.jdk8NewFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Demo1 {

    public static void main(String[] args) {
        InterExample<String,Integer> i = (a) -> Integer.valueOf(a);
//        InterExample<String,Integer> i = Integer::valueOf;
        System.out.println(i.run("123"));
        List<Integer> list = new ArrayList<>();
        list.stream();

    }
}
