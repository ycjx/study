package com.yxj.thread;

public class MtThread2 extends Thread {

    @Override
    public void run() {
        super.run();
//        System.out.println("运行完毕");
        for (int i=0;i<50;i++){
            try {

            }catch (Exception e){

            }

            System.out.println("i="+i);
            if(Thread.interrupted()){
                break;
            }

        }
    }
}
