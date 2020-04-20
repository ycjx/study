package com.yxj.spi;

import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-10 14:56
 */
public class SearchFile implements Search {

    @Override
    public List<String> search() {
        System.out.println("file");
        return null;
    }
}
