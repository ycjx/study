package com.yxj.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-02-09 22:47
 */
public class CallTaskDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 8, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));

        Future<Object> submit = threadPoolExecutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });





    }
}
