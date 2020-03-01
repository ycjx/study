package com.yxj.lambda;

import jdk.nashorn.internal.objects.annotations.Function;

import java.util.Optional;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-11-22 15:23
 */
public class Option {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String name;

    private String age;

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    private Option option;

    public static void main(String[] args) throws Exception {
        Option a = null;
        Optional<Option> a1 = Optional.ofNullable(a);

        String kjjk = a1.flatMap(vo -> Optional.ofNullable(vo.getOption()))
                .map(Option::getName).orElseThrow(Exception::new);

        System.out.println(kjjk);
    }
}
