package com.yxj.designPatterns.decoratorPattern;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/7 上午12:00
 */
public class Mocha extends Decorator {


    private Beverager beverager;

    public Mocha(Beverager beverager) {
        this.beverager = beverager;
    }

    @Override
    public String getDescription() {
        return "摩卡";
    }

    @Override
    public int cost() {
        return 5 + beverager.cost();
    }
}
