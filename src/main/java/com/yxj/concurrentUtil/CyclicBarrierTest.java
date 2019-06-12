package com.yxj.concurrentUtil;

import java.util.concurrent.CyclicBarrier;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-06-03 15:47
 */
public class CyclicBarrierTest {


    private static CyclicBarrier cyclicBarrier;

    static class CyclicBarrierThread extends Thread{
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了");
            //等待
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class CyclicBarrierThread2 extends Thread{
        public void run() {
            try {
                Thread.sleep(20000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("最后一个" + "到了");
            //等待
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("人到齐了，开会吧....");
            }
        });

        for(int i = 0 ; i < 4 ; i++){
            new CyclicBarrierThread().start();
        }
        new CyclicBarrierThread2().start();

    }
}
