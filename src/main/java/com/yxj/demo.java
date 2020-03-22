package com.yxj;

public class demo {

    public static void main(String args[]) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("begin");
                Thread.currentThread().interrupt();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end");

            }
        });
        a.start();
    }


}


