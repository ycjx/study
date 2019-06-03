package com.yxj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        HashMap<Object, List> objectObjectHashMap = new HashMap<>();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("asd");
        objectObjectHashMap.put("aa",objects);
        objects = new ArrayList<>();
        System.out.println(objectObjectHashMap.get("aa").size());
    }


    public static boolean Com() {
        int i = 1;
        return i == 1 ? true : false;
    }
}
