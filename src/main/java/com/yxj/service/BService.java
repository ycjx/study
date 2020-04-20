package com.yxj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-06 17:36
 */
@Service
public class BService {

    @Autowired
    private CService cService;

    public void test() {
        System.out.println("bService");
    }
}
