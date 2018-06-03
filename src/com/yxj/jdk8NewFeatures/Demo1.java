package com.yxj.jdk8NewFeatures;

public class Demo1 {

    public static void main(String[] args) {
//        InterExample<String,Integer> i = (a) -> Integer.valueOf(a);
        InterExample<String,Integer> i = Integer::valueOf;
        System.out.println(i.run("123"));
    }
}
