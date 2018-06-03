package com.yxj;

public class RunableA extends Thread {

    Father f;


    public RunableA(Father f){
        this.f = f;
    }

    @Override
    public void run() {
        f.run2();
    }
}
