package com.yxj.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author:yuxj
 * @descriptio 虚引用必须和引用队列(ReferenceQueue)联合使用。
 *             当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，
 *             就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
 *
 * @create:2019-05-11 16:34
 */
public class PhantomReferenceTest {

    public static int M = 1024 * 1024;

    public static void printlnMemory(String tag) {
        Runtime runtime = Runtime.getRuntime();
        int M = PhantomReferenceTest.M;
        System.out.println("\n" + tag + ":");
        System.out.println(runtime.freeMemory() / M + "M(free)/" + runtime.totalMemory() / M + "M(total)");
    }

    public static void main(String[] args) throws InterruptedException {

        PhantomReferenceTest.printlnMemory("1.原可用内存和总内存");
        byte[] object = new byte[10 * PhantomReferenceTest.M];
        PhantomReferenceTest.printlnMemory("2.实例化10M的数组后");

        //建立虚引用
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
        PhantomReference<Object> phantomReference = new PhantomReference<Object>(object, referenceQueue);

        PhantomReferenceTest.printlnMemory("3.建立虚引用后");
        System.out.println("phantomReference : " + phantomReference);
        System.out.println("phantomReference.get() : " + phantomReference.get());
        System.out.println("referenceQueue.poll() : " + referenceQueue.poll());

        //断开byte[10*PhantomReferenceTest.M]的强引用
        object = null;
        PhantomReferenceTest.printlnMemory("4.执行object = null;强引用断开后");

//        byte[] object2 = new byte[234 * PhantomReferenceTest.M];


        System.gc();
        PhantomReferenceTest.printlnMemory("5.GC后");
        System.out.println("phantomReference : " + phantomReference);
        System.out.println("phantomReference.get() : " + phantomReference.get());
        System.out.println("referenceQueue.poll() : " + referenceQueue.poll());

        //断开虚引用
        phantomReference = null;
        System.gc();
        PhantomReferenceTest.printlnMemory("6.断开虚引用后GC");
        System.out.println("phantomReference : " + phantomReference);
        System.out.println("referenceQueue.poll() : " + referenceQueue.poll());
    }
}
