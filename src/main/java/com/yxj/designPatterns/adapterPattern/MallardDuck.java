package com.yxj.designPatterns.adapterPattern;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/6 下午10:42
 */
public class MallardDuck implements Duck {


    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("Fly");
    }
}
