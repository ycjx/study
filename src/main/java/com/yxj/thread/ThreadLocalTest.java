package com.yxj.thread;

import java.lang.ref.WeakReference;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/11 下午3:29
 */
public class ThreadLocalTest {


    public static void main(String[] args) throws InterruptedException {

        ThreadLocal threadLocal = new ThreadLocal();


        Object object = new Object();

        threadLocal.set(object);
        threadLocal = null;
        object = null;
        threadLocal.remove();
        System.gc();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadLocal.get());
        WeakReference<ThreadLocalTest> threadLocalTestWeakReference = new WeakReference<ThreadLocalTest>(new ThreadLocalTest());
//        threadLocalTestWeakReference.get();
        System.gc();
        Thread.sleep(5000);

        if(threadLocalTestWeakReference.get() == null){
            System.out.println("被回收喽");
        }


    }
}
