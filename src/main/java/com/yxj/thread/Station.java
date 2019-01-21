package com.yxj.thread;

public class Station {

    public  int tricketsSoldss = 6;

    public  int tricketsSold = 6;

    public Boolean bool = false;

    public int getTricketsSold() {
        return tricketsSold;
    }

    public void setTricketsSold(int tricketsSold) {
        this.tricketsSold = tricketsSold;
    }

    public   void sellTricket(String a){
        synchronized(a){
            tricketsSold++;
            System.out.println("已经出售"+tricketsSold+"张车票");
        }


        System.out.println(Thread.currentThread().getName());

//        try {
//            Thread.sleep(2000);
//            this.notifyAll();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }


    }
}
