package com.yxj.arithmetic;

/**
 * @author:yuxj
 * @descriptio 整数反转
 * @create:2019-02-26 00:53
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }


    public static int reverse(int x) {

        int result = 0;
        boolean bool = x > 0 ? true : false;
        while (x != 0) {
            if(bool){
                if((Integer.MAX_VALUE-x%10)/10 < result ){
                    return 0;
                }
            }else{
                if((Integer.MIN_VALUE-x%10)/10 > result ){
                    return 0;
                }
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }

        return result;
    }


    public static void binary(int num) {

        for (int i = 31; i >= 0; i--) {
            System.out.print(num >> i & 1);
        }
    }
}
