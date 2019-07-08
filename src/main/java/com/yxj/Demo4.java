package com.yxj;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-07-01 19:01
 */
public class Demo4 {


    public static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(Demo4::test,"线程1");
        Thread t2 = new Thread(Demo4::test,"线程2");
        Thread t3 = new Thread(Demo4::test,"线程3");
        t1.start();
        t1.join();
        System.out.println("主线程醒了");
        t2.start();
        t2.join();

        t3.start();
        t3.join();
    }


    public static void test(){
        System.out.println(Thread.currentThread().getName());
        Thread t1 = new Thread(Demo4::test,"线程1");
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("子线程醒了");
    }
}
