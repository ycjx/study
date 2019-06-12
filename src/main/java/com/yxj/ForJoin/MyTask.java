package com.yxj.ForJoin;


import java.util.concurrent.ForkJoinTask;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-06-12 15:36
 */
public class MyTask extends ForkJoinTask<Integer> {


    private Integer reslut;

    @Override
    public Integer getRawResult() {
        return reslut;
    }

    @Override
    protected void setRawResult(Integer value) {
        reslut = value;
    }

    @Override
    protected boolean exec() {
        System.out.println("不知道干什么就打一行子吧");
        setRawResult(10);
        return false;
    }
}
