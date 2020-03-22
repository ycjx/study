package com.yxj.concurrentUtil;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-06 18:08
 */
public class ConcurrentLinkedQueueTest {


    private static ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

    public static void main(String[] args) {

        concurrentLinkedQueue.add("a");
        concurrentLinkedQueue.add("b");
        concurrentLinkedQueue.add("c");
        concurrentLinkedQueue.add("d");
        concurrentLinkedQueue.add("e");
        concurrentLinkedQueue.add("f");
        concurrentLinkedQueue.offer("z");

        System.out.println(concurrentLinkedQueue.peek());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.peek());


    }
}
