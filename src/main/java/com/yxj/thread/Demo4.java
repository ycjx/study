package com.yxj.thread;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-02-09 22:58
 */
public class Demo4 {

    private Object object = new Object();

    private static boolean keepRunning = true;

    public static void main(String[] args) throws Exception {
        Demo4 demo4 = new Demo4();
        demo4.testA();
        demo4.testB();
    }


    public void testA() {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("a睡着咯");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("a醒咯");
                }
            }
        });
        a.start();
    }

    public void testB() {
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("b睡着咯");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("b醒咯");
                }
            }
        });
        b.start();
    }
}
