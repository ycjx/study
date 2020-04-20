package com.yxj.JdkProxy;

public class HelloWorldImpl implements HelloWorld {
    @Override
    public void sayHello() {
        System.out.println("HelloWorld");
        hehe();
    }

    @Override
    public void hehe() {
        System.out.println("hehe");
    }
}
