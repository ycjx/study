package com.yxj.designPatterns.decoratorPattern;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.LineNumberInputStream;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/7 上午12:32
 */
public class Test {

    /**
     * 装饰者模式
     *
     * 加上新的行为 不改动现有的代码和接口
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        Beverager beverager = new Milk(new Mocha(new HouseBlend()));

        System.out.println(beverager.cost());

    }
}
