package com.yxj.concurrentUtil;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-02-12 15:45
 */
public class SynchronizedTest {

    private static SynchronizedTest synchronizedTest = new SynchronizedTest();

    public static synchronized void staticMethod() throws InterruptedException {
        System.out.println("静态同步方法开始");
        Thread.sleep(1000);
        System.out.println("静态同步方法结束");
    }

    public static void main(String[] args) {
        synchronized (synchronizedTest){
            System.out.println(1);
        }
    }

}
