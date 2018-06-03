package com.yxj;

import java.util.ArrayList;
import java.util.List;


//排列组合
public class Demo1 {

    private int n;

    private static ArrayList<String> list = new ArrayList<String>();

    private static void array(List<String> a, int length , String s){

        if(length<1){
            System.out.println(s);
            list.add(s);
            return;
        }

        for(int i=0;i<a.size();i++){

            List<String> c = new ArrayList<String>();
            c.addAll(a);
            String temp = c.get(0);
            c.set(0,c.get(i));
            c.set(i,temp);
            String s2 = s;
            s2 = s2+c.get(0);
            List<String> b =  new ArrayList<String>();
            b.addAll(c.subList(1,c.size()));
//            System.out.println(c);
            array(b,length-1,s2);

        }



    }

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add("e");

        String s = "";
//        ArrayList<String> b = new ArrayList<String>();
//        b.addAll(a);
//        b.set(3,"asda");
//        System.out.println(a.get(3));
        array(a,5,s);
        System.out.println(list.size());
    }



}
