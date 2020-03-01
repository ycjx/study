package com.yxj.thread;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-02-22 16:36
 */
public class JoinDemo {

    public static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("hehe");
                    try {
                        Thread.sleep(5000);
                        System.out.println("haha");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("bebe");

                try {
                    a.join();
                    Thread.sleep(5000);
                    System.out.println("baba");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        a.start();
        b.start();
        a.join();
        System.out.println("xixi");
    }
}
