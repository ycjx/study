package com.yxj;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(46800);
        BigDecimal b = new BigDecimal(70200);
        System.out.println(a.divide(b).setScale(2).doubleValue());
    }


    public static boolean Com() {
        int i = 1;
        return i == 1 ? true : false;
    }
}
