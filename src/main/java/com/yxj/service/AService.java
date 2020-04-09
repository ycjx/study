package com.yxj.service;

import com.yxj.KogMawEvent;
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


    @Transactional(rollbackFor = Exception.class)
    public void test() {
        applicationContext.publishEvent(new KogMawEvent(UUID.randomUUID().toString()
                , Thread.currentThread().getName()));
        applicationContext.publishEvent(new KogMawEvent(UUID.randomUUID().toString()
                , Thread.currentThread().getName()));
        System.out.println(1);
    }
}
