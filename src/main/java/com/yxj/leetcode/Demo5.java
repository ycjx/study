package com.yxj.leetcode;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Demo5 {
    public static void main(String[] args) {
        long a = new Date().getTime();
        Instant in = Instant.ofEpochMilli(a);
        System.out.println(LocalDateTime.ofInstant(in, ZoneId.systemDefault()));
    }
}
