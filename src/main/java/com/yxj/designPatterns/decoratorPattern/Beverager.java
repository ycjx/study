package com.yxj.designPatterns.decoratorPattern;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/6 下午11:52
 */
public abstract class Beverager {

    private String description;

    public String getDescription() {
        return description;
    }

    public abstract int  cost();
}
