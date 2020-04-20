package com.yxj.spi;

import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-10 14:55
 */
public class SearchDb implements Search {


    @Override
    public List<String> search() {
        System.out.println("db");
        return null;
    }
}
