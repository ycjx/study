package com.yxj.example;

public abstract class Father {



    public  int  print(int a){
        System.out.println("fatherg+int");
        return 1;
    }

    public void print(String a){
        System.out.println(a);
        this.print(1);
    }
}
