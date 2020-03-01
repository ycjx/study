package com.yxj.thread;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-02-09 22:58
 */
public class Demo4 {


    private static boolean keepRunning = true;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            while (keepRunning) {
                // doSomething
                try {
                    Thread.sleep(1);
                }catch (InterruptedException ex){

                }
            }
            System.out.println("停止循环");
        }).start();
        Thread.sleep(500);
        keepRunning = false;
        System.out.println("改变停止标志");
    }
}
