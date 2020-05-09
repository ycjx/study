package com.yxj.service;

import com.yxj.KogMawEvent;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-06 17:35
 */
@Service
public class AService {

    @Autowired
    private BService bService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AService aService;


    @Transactional
    public void test() {
        applicationContext.publishEvent(new KogMawEvent("test"
                , Thread.currentThread().getName()));
        System.out.println(1);
        test2();
    }


    @Transactional
    public void test2() {
        applicationContext.publishEvent(new KogMawEvent("test2"
                , Thread.currentThread().getName()));
        System.out.println(1);
    }


}
