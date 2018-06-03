package com.yxj.thread;



public class Demo1 {

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setName("线程A");

        final String a = "a";

        for(int i=0;i<20;i++){
            final Station sta = new Station();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

//                    Thread.sleep(s);
                    sta.sellTricket(a);
                    System.out.println(Thread.currentThread().getName());


                }
            });
            thread.setName("线程"+i);
            thread.start();
        }
        Thread.sleep(1000);
//        MyThread1 th = new MyThread1(sta);
//        th.setName("线程Mythread");
//        th.start();
//        try {
//            Thread.sleep(2000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        MyThread1 t2 = new MyThread1(sta);
//        t2.start();
//        try {
//            Thread.sleep(2000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        System.out.println(th.getState());
//        System.out.println(t2.isAlive());

    }
}
