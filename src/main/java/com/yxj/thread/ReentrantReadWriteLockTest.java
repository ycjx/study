package com.yxj.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-04 11:12
 */
public class ReentrantReadWriteLockTest {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Runnable() {

            @Override
            public void run() {
                lock.writeLock().lock();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aaaaa");

                System.out.println("writeLock.unlock");
            }
        });


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.readLock().lock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("bbbbbbb");
                lock.readLock().unlock();
                System.out.println("bbbbbb readLock.unlock");
            }
        });


        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.readLock().lock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("cccccc");
                lock.readLock().unlock();
                System.out.println("ccccc readLock.unlock");
            }
        });

        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.writeLock().lock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("dddddd");
                lock.writeLock().unlock();
                System.out.println("dddddd writeLock.unlock");
            }
        });


        a.start();
        lock.writeLock().unlock();
        Thread.sleep(500);
        b.start();
        Thread.sleep(500);
        d.start();
        Thread.sleep(500);
        c.start();


    }
}
