package com.yxj.leetcode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//一个由小写字母组成的字符串可以看成一些同一字母的最大碎片组成的。
//        例如,"aaabbaaac"是由下面碎片组成的:'aaa','bb','c'。
//        牛牛现在给定一个字符串,请你帮助计算这个字符串的所有碎片的平均长度是多少。

public class Demo3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder(in.nextLine());
        List<Integer> list = new ArrayList<Integer>();
        list=art(list,sb);
        System.out.println(list.toString());
        int sum =  list.stream().reduce(0,Integer::sum);
        System.out.println(sum);
        double avg = sum/((double)list.size());
        DecimalFormat df = new DecimalFormat("##0.00");
//        System.out.println(new BigDecimal(avg).setScale(2, RoundingMode.HALF_UP).doubleValue());
        System.out.println(df.format(avg));


    }

    private static List<Integer> art(List<Integer> list,StringBuilder sb){
        if(sb == null || sb.length() == 0)
            return list;
        int count = 1;
        if(sb.length()==1){
            list.add(1);
            return list;
        }
        for(int i=1;i<sb.length();i++){
            if(sb.charAt(i) == sb.charAt(i-1)){
                count++;
            }else{
                list.add(count);

                return art(list,sb.delete(0,i));
            }

        }
        list.add(count);
        return list;
    }
}
