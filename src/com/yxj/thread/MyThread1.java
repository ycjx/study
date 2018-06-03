package com.yxj.thread;

public class MyThread1 extends  Thread {

    private Station station;

    String a;

    public  MyThread1(){

    }

    public MyThread1(Station station,String a){
        if(this.station == null){
            this.station = station;
        }

        if(this.a == null){
            this.a = a;
        }
//       System.out.println("-----------构造方法中的this.getName的名字:"+this.getName());
//        System.out.println("-----------构造方法中的Thread.currentThread().getName()的名字:"+Thread.currentThread().getName());
    }

    @Override
    public void run() {
        super.run();
//        System.out.println("=============run方法中的this.getName的名字:"+this.getName());

        this.station.sellTricket(a);
//        System.out.println(Thread.currentThread()==this);
//        System.out.println(Thread.currentThread().getName());
    }
}
