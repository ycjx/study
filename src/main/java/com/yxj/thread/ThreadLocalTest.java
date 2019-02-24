package com.yxj.thread;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/11 下午3:29
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("main");
        System.out.println(threadLocal.get());
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        });
        a.start();




    }
}
