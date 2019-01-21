package com.yxj.stream;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class Demo1 {
    public static void main(String[] args) {

        Shop p1 = new Shop("a",5);
        Shop p2 = new Shop("b",8);
        Shop p3 = new Shop("c",3);
        Shop p4 = new Shop("d",6);
        Shop p5 = new Shop("e",9);

        List<Shop>  list = Arrays.asList(p1,p2,p3,p4,p5);
        long count = list.stream()
                .filter(x -> x.getDistance() > 5)
                .count();


    }

    /**
     * 找到离我最近的商店
     * @param list
     */
    public static void findFirstCloseShop(List<Shop> list){
        String name = list.stream()
                .sorted(Comparator.comparingInt(x -> x.getDistance()))
                .findFirst()
                .get().getName();
        System.out.println(name);
    }


}
