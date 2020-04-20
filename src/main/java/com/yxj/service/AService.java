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
public class AService  implements FatherService{

    @Autowired
    private BService bService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AService aService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test() {
        applicationContext.publishEvent(new KogMawEvent("test"
                , Thread.currentThread().getName()));
        System.out.println(1);
        aService.test2();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test2() {
        applicationContext.publishEvent(new KogMawEvent("test2"
                , Thread.currentThread().getName()));
        System.out.println(1);
    }


}
