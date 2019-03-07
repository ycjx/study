package com.yxj.JdkProxy;

public class Test {

    public static void main(String[] args) {
        HelloProxy helloHandler = new HelloProxy();

        HelloWorld hello =(HelloWorld) helloHandler.bind(new HelloWorldImpl());

        hello.sayHello();
        String a = new String("a");
        String b = "a";
        System.out.println(a==b);
        a.intern();
        System.out.println(a==b);



    }
}
