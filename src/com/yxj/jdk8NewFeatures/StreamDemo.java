package com.yxj.jdk8NewFeatures;

import java.util.ArrayList;
import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("acc");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("ads");
        filter(list);

    }

    public static  void  filter(List<String> l){
        l.stream().filter((s)-> s.startsWith("a")).forEach(System.out::println);
    }
}
