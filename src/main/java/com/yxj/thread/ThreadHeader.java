package com.yxj.thread;

import org.openjdk.jol.info.ClassLayout;
import sun.jvm.hotspot.tools.jcore.ClassDump;

import java.nio.ByteOrder;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-30 14:42
 */
public class ThreadHeader {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    //    private static Object a = new Object();


    public void test2() {


        ThreadHeader a = new ThreadHeader();
//
//        System.out.println(ClassLayout.parseInstance(a).toPrintable());
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                synchronized (a) {
////                    System.out.println(Thread.currentThread().getId());
////                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
////
////                }
////            }
////        }).start();

        synchronized (a){
            System.out.println(Thread.currentThread().getId());
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }


    public void test() {
        Object a = new Object();

//        System.gc();
        System.out.println(ByteOrder.nativeOrder());
//        System.out.println(a.hashCode());
        //打印出来的是小端序
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println(Thread.currentThread().getId());
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());

                }
            }
        });
//        Thread b = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (a){
//                    try {
//                        Thread.sleep(5000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        b.start();


        c.start();
//        try {
//            c.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        synchronized (a){
//            System.out.println(ClassLayout.parseInstance(a).toPrintable());
//        }



    }

    public static void main(String[] args) {
        ThreadHeader threadHeader = new ThreadHeader();
        threadHeader.test();


    }
}
