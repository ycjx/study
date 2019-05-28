package com.yxj.jdk8NewFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Demo1 {

    public static void main(String[] args) {
        Double a = 1.2;
        Double b = 1.7;
        Double c =1.0;
        System.out.println(a.intValue());
        System.out.println(a>a.intValue());
        System.out.println(b.intValue());
        System.out.println(b>a.intValue());
        System.out.println(c.intValue());
        System.out.println(c>a.intValue());

    }
}
