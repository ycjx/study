package com.yxj.DesignPatterns.DecoratorPattern;

public abstract class Beverage {

    private String description = "Ünknown Beverage";

    public String getDescription(){
        return  this.description;
    }

    public double cost(){
        return 1.1;
    }


}
