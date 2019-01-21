package com.yxj.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockQueueDemo {

    public static void main(String[] args) {


        final BlockingQueue bq = new ArrayBlockingQueue(5);
        bq.add(1);
        bq.add(2);
        bq.add(3);
        bq.add(4);
        bq.add(5);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bq.put(6);
                    System.out.println("线程1把6放入队列");
                }catch (InterruptedException e){

                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2准备从队列中拿出东西");
                try {
                    Thread.sleep(2000);
                    System.out.println("线程2拿出了"+bq.take());
                }catch (InterruptedException e){

                }

            }
        });
        t1.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){

        }
        t2.start();



       while (true){

       }
    }
}
