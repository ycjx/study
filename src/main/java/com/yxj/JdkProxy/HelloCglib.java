package com.yxj.JdkProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class HelloCglib {

    public static void main(String[] args) {
        methodInterCeptor();
    }


    public static void methodInterCeptor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Car.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
                System.out.println("before");
                Object res = methodProxy.invokeSuper(obj, args);
//                Object res = method.invoke(obj,args);
                System.out.println("after");
                return res;
            }
        });
        Car car = (Car) enhancer.create();
        car.print();
    }

    public static void invocationHandler() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Car.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                System.out.println("after");
                return null;
            }
        });
        Car car = (Car) enhancer.create();
        car.print();
    }

    static class Car {
        void print() {
            System.out.println("I am a car");
            printDog();
        }

        void printDog() {
            System.out.println("I am a dog");
        }
    }
}
