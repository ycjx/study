package com.yxj.thread;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/11 下午3:29
 */
public class ThreadLocalTest {

    private static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {

        threadLocal.set("main");
        System.out.println(threadLocal.get());
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("123");
                System.out.println(threadLocal.get());
            }
        });
        a.start();



    }
}
