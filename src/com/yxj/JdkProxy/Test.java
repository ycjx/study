package com.yxj.JdkProxy;

public class Test {

    public static void main(String[] args) {
        HelloProxy helloHandler = new HelloProxy();

        HelloWorld hello =(HelloWorld) helloHandler.bind(new HelloWorldImpl());

        hello.sayHello();
    }
}
