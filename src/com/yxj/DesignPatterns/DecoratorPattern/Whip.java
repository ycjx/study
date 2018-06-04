package com.yxj.DesignPatterns.DecoratorPattern;

public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost()+0.55;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+"Whip";
    }
}
