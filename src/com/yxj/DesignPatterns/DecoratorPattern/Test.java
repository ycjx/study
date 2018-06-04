package com.yxj.DesignPatterns.DecoratorPattern;

public class Test {

    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);
        System.out.println(beverage.cost());
        System.out.println(beverage.getDescription());
    }
}
