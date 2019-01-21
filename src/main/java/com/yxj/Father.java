package com.yxj;

public class Father {

    synchronized public void run2(){
        System.out.println("father");
        try {
            Thread.sleep(5000);
            System.out.println("father22");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public  void run11(){
        System.out.println("äda");
        try {
            Thread.sleep(5000);
            System.out.println("äda111");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
