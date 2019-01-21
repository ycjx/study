package com.yxj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;

public class demo {

    public static void main(String args[]) {
//        RunableA ARun = new RunableA();
//        Thread a = new Thread(ARun);
//        ThreadB b = new ThreadB();
//        Callable<String> cCall = new CallA();
//        FutureTask<String> futureTask = new FutureTask<String>(cCall);
//        Thread c = new Thread(futureTask);
//
//
//        List<User> users = new ArrayList<User>();
//        users.add(new User(20, "张三"));
//        users.add(new User(22, "李四"));
//        users.add(new User(10, "王五"));
//
//        Stream<User> stream = users.stream();
//        //所有的年龄大于20岁的User对象，转换为字符串50对象。现在流中只有字符串对象了。
//        long count = stream.filter((User user) ->  user.getAge() > 20).map((User user) -> {return "50";}).count();
//        System.out.print(count);

        String source = "F:\\BaiduNetdiskDownload\\yjwu";
        String target = "F:\\BaiduNetdiskDownload\\11";
        File sourceFile = new File(source);
        File targetFile = new File(target);
        File[] fileList= sourceFile.listFiles();
        for(File f : fileList){
            String name = f.getName();
            if(f.isDirectory()){
                File[] pictList = f.listFiles();
                for(File c : pictList){
                    c.renameTo(new File("F:\\BaiduNetdiskDownload\\11\\"+name.substring(0,3)+"-"+c.getName()));
                }
            }
        }

        HashMap a = new HashMap();



    }

}


