package com.yxj.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setName("线程A");

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(() -> {
            System.out.println(1);
            lock.lock();
            System.out.println("第一个任务");
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException ex) {

            } finally {
                lock.unlock();
            }
        });
        thread.start();


        Thread thread2 = new Thread(() -> {
            lock.lock();
            System.out.println("第二个任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {

            } finally {
                lock.unlock();
            }
        });
        thread2.start();
        Thread.sleep(5000);
//        Thread thread3 = new Thread(() -> {
//            lock.lock();
//            System.out.println("第三个任务");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//
//            } finally {
//                lock.unlock();
//            }
//        });
//        thread3.start();


    }
}
