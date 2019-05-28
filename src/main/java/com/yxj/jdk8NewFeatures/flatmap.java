package com.yxj.jdk8NewFeatures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-05-14 16:38
 */
public class flatmap {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("asdf");
        words.add("qsdf");
//        List a = words.stream()
//                .map(word -> word.split(""))
//                .distinct()
//                .collect(toList());

        List a = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(toList());
        System.out.println(a);
    }


}
