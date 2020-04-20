package com.yxj.JdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloProxy implements InvocationHandler {

    private Object taget;

    public  Object bind(Object taget){
        this.taget = taget;
        return Proxy.newProxyInstance(taget.getClass().getClassLoader(),taget.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if(method.getName().equals("hehe")){
            System.out.println("hehe方法执行前");
        }else{
            System.out.println("hello方法执行前");
        }
        result = method.invoke(taget,args);
        System.out.println("执行方法后");
        return null;
    }
}
