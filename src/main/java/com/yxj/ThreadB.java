package com.yxj;

public class ThreadB extends  Thread {

    Father f;

    public ThreadB(Father f){
        this.f = f;
    }
    public void run(){
        f.run11();
    }
}
