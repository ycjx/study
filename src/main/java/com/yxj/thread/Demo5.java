package com.yxj.thread;

import java.lang.ref.WeakReference;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-02-09 23:21
 */
public class Demo5 {


    public static volatile int j = 0;

    public static int i = 0;

    public static  Object object = new Object();

    public static void main(String[] args) throws InterruptedException {

//
//        Thread a = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (object){
//                    while (j == 0) {
//                        i++;
//                        System.out.println("a线程-------i:" + i);
//
//                    }
//                }
//            }
//        });
//
//        Thread b = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (i == 0) {
//
//                }
//                j = 1;
//                System.out.println("b线程-------i:" + i);
//            }
//        });
//        a.start();
//        b.start();
//        a.join();
//        b.join();

        ThreadLocalMap threadLocalMap1 = new ThreadLocalMap();

        ThreadLocalMap threadLocalMap2 = new ThreadLocalMap();
        System.out.println(1);


    }

    static class ThreadLocalMap {


    }
}
