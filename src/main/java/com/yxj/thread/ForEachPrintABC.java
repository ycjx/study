package com.yxj.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-05 17:18
 */
public class ForEachPrintABC {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition conditionA = lock.newCondition();

    private static Condition conditionB = lock.newCondition();

    private static Condition conditionC = lock.newCondition();

    private static volatile int i = 1;

    public static void main(String[] args) {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(i % 3 == 1){
                        System.out.println("A");
                        i++;
                    }
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(i % 3 == 2){
                        System.out.println("B");
                        i++;
                    }
                }
            }
        });


        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(i % 3 == 0){
                        System.out.println("C");
                        i++;
                    }
                }
            }
        });
        a.start();
        b.start();
        c.start();

    }
}
