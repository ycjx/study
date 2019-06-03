package com.yxj.thread;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-05-28 20:04
 */
public class ScheduledExecutorServiceTest {


    public static void main(String[] args) {
        System.out.println("生成线程池："+System.currentTimeMillis()/1000);
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("🐕"+System.currentTimeMillis()/1000);
            }
        },3000,3000, TimeUnit.MILLISECONDS);
    }
}
