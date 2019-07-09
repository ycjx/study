package com.yxj.leetcode;

/**
 * @author:yuxj
 * @descriptio Z 字形变换
 * @create:2019-04-06 01:00
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        ZigZagConversion zigZagConversion = new ZigZagConversion();

        System.out.println(zigZagConversion.convert("PAYPALISHIRING",3));
    }


    public String convert(String s, int numRows) {
        if (s == null)
            return null;
        if ("".equals(s.trim()))
            return "";
        int length = s.length();
        if(numRows == 1)
            return s;
        if(length<=numRows)
            return s;
        char[] c = s.toCharArray();
        char[] d = new char[length];
        int group = (numRows-1)*2;
        int index = length/group+1;
        int k = 0;
        for (int i = 0; i < numRows ; i++) {

            for(int j=0;j<index;j++){
                int a = i+j*group;
                if(a<length){
                    d[k++] = c[a];
                }
                if(i>0&&i<numRows-1&& (group-i+j*group)<length){
                    d[k++] = c[group-i+j*group];
                }
            }
        }
        return new String(d);
    }
}
