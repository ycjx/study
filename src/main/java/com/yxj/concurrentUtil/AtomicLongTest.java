package com.yxj.concurrentUtil;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-06 18:16
 */
public class AtomicLongTest {

    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) {
        atomicLong.compareAndSet(12,11);
    }
}
