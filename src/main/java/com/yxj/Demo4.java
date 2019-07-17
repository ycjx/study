package com.yxj;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-07-01 19:01
 */
public class Demo4 {


    public static Object object = new Object();


    /**
     * 在底册会调用   lock.notify_all(thread);//注意这里  来唤醒阻塞的线程
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("哈哈");
        Thread t0 = new Thread(threadGroup, Demo4::test2, "线程0");
        Thread t1 = new Thread(threadGroup, Demo4::test, "线程1");
        Thread t2 = new Thread(Demo4::test, "线程2");
        Thread t3 = new Thread(Demo4::test, "线程3");

        t0.start();
        t1.start();
        System.out.println("线程组有多少线程数："+threadGroup.activeCount());
        t1.join();
        System.out.println("线程组有多少线程数："+threadGroup.activeCount());
        t2.start();
        t2.join();
        System.out.println("线程组有多少线程数："+threadGroup.activeCount());
        t3.start();
        t3.join();
    }


    public static void test() {
        System.out.println(Thread.currentThread().getName());
//        try {
//            object.wait(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("子线程醒了");
    }

    public static void test2() {

        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
