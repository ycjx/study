package com.yxj.designPatterns.adapterPattern;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/6 下午10:44
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("gobble");
    }

    @Override
    public void fly() {
        System.out.println("turkey is flying");
    }
}
