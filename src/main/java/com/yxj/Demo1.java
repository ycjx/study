package com.yxj;

import java.util.ArrayList;
import java.util.List;


public class Demo1 {


    public static void main(String[] args) {
        User a = new User(1,"a");
        User b = new User(12,"b");
        User c = new User(12,"c");
        List<User> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);

        System.out.println(list.stream().map(User::getAge).reduce(Integer::max));
        System.out.println(list.stream().map(User::getAge).reduce(Integer::sum));



    }





}
