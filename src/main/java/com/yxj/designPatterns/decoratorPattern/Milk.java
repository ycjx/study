package com.yxj.designPatterns.decoratorPattern;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/7 上午12:05
 */
public class Milk extends Decorator {

    private Beverager beverager;


    public Milk(Beverager beverager) {
        this.beverager = beverager;
    }

    @Override
    public String getDescription() {
        return "牛奶";
    }

    @Override
    public int cost() {
        return 10 + beverager.cost();
    }
}
