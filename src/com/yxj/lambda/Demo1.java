package com.yxj.lambda;

import java.util.function.Consumer;

public class Demo1 {

    /**
     * expression = (variable) ->action
     * variable :指变量
     * action : 实现的代码逻辑部分
     *
     *
     *
     */

    interface RunCode<T>{
        void run(String var);
    }

    public static void main(String[] args) {
//        RunCode r = (String x) -> System.out.println(x);
//        r.run("x");

        conSumerExample(10,money -> System.out.println(money));
    }


    /**
     * 消费接口示例
     * @param money
     * @param consumer
     */
    public static void conSumerExample(Integer money, Consumer<Integer> consumer){
        consumer.accept(money);
    }
}
