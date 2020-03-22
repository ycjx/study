package com.yxj;

import java.math.BigDecimal;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-05 14:26
 */
public class BigNumTest {

    public static void main(String[] args) {
        System.out.println(0.1+2001299.32);

        Double d1 = 0.11;
        Double d2 = 2001299.32;
        BigDecimal a = new BigDecimal(d1);
        BigDecimal b = new BigDecimal(d2);

        System.out.println(a.add(b).toString());
    }
}
