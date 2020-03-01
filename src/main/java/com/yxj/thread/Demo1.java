package com.yxj.thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo1 {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 8, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));
        System.out.println(threadPoolExecutor.getPoolSize());

        for (int i = 0; i < 2; i++) {
            int j = 0;
            FutureTask futureTask = new FutureTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println("-----" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, j);
            Future<?> submit = threadPoolExecutor.submit(futureTask);
        }
        System.out.println("threadPoolExecutor.getPoolSize----" + threadPoolExecutor.getPoolSize());

        System.out.println("threadPoolExecutor.getActiveCount----" + threadPoolExecutor.getActiveCount());

        System.out.println("threadPoolExecutor.getQueue().size()----" + threadPoolExecutor.getQueue().size());
        Thread.sleep(20000);

        System.out.println("threadPoolExecutor.getPoolSize----" + threadPoolExecutor.getPoolSize());

        System.out.println("threadPoolExecutor.getActiveCount----" + threadPoolExecutor.getActiveCount());

        System.out.println("threadPoolExecutor.getQueue().size()----" + threadPoolExecutor.getQueue().size());


        for (int i = 0; i < 10; i++) {
            int j = 0;
            FutureTask futureTask = new FutureTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println("-----" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, j);
            Future<?> submit = threadPoolExecutor.submit(futureTask);
        }



    }
}
