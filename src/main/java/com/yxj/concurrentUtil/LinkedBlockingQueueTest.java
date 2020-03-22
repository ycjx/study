package com.yxj.concurrentUtil;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-06 18:12
 */
public class LinkedBlockingQueueTest {

    private static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

    public static void main(String[] args) {

        linkedBlockingQueue.add("a");
        linkedBlockingQueue.add("b");
        linkedBlockingQueue.add("c");
        linkedBlockingQueue.add("d");
        linkedBlockingQueue.add("e");
        linkedBlockingQueue.add("f");
        linkedBlockingQueue.offer("z");

        try {
            linkedBlockingQueue.put("y");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(linkedBlockingQueue.peek());
        System.out.println(linkedBlockingQueue.poll());
        System.out.println(linkedBlockingQueue.poll());
        System.out.println(linkedBlockingQueue.peek());

    }
}
