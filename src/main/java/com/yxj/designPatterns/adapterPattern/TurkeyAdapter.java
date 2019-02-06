package com.yxj.designPatterns.adapterPattern;

/**
 * @author:yuxj
 * @descriptio  对象适配器模式 需要调用b接口的方法但是只有a接口 所以需要适配器
 * @create:2019/2/6 下午10:45
 */
public class TurkeyAdapter implements Duck {

    private Turkey turkey;


    public TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        turkey.fly();
    }
}
