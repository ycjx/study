package com.yxj.thread;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-23 09:48
 */
public class ViolateExample {

    static boolean flag = false;

    static Integer i = 0;


    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(100);
                flag = true;
                System.out.println("flag - true");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        while (!flag) {
            if (i < 128) {
                i++;
            } else {
                i = 0;
            }
        }

        System.out.println("程序结束了 i：" + i);


    }
}
