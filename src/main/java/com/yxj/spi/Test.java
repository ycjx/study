package com.yxj.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-10 14:58
 */
public class Test {


    public static void main(String[] args) {
        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = load.iterator();
        while (iterator.hasNext()){
            Search next = iterator.next();
            next.search();
        }
    }
}
