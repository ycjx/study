package com.yxj.concurrentUtil;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-06 18:14
 */
public class ConcurrentLinkedDequeTest {

    private static ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();

    public static void main(String[] args) {

        concurrentLinkedDeque.addLast("a");
        concurrentLinkedDeque.addFirst("s");
        concurrentLinkedDeque.offer("b");

    }
}
