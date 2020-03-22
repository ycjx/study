package com.yxj.thread;

import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-04 18:14
 */
public class ReentrantLockTest {

    private static ReentrantLock reentrantLock = new ReentrantLock();


    public static void main(String[] args) {
        Condition condition = reentrantLock.newCondition();

        Thread a = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println("aaaaa.await");
                    condition.await();
                    System.out.println("aaaaa.signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            }
        });


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println("bbbbbbb.await");
                    condition.await();
                    System.out.println("bbbbbbb.signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }finally {
                    reentrantLock.unlock();
                }
            }
        });


        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println("cccccc.await");
                    condition.await();
                    System.out.println("cccccc.signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }finally {
                    reentrantLock.unlock();
                }
            }
        });

        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println("dddddddd.await");
                    condition.await();
                    System.out.println("dddddddd.signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    reentrantLock.unlock();
                }
            }
        });
        a.start();
        b.start();
        c.start();
        d.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.lock();
        condition.signalAll();
        reentrantLock.unlock();

    }
}
