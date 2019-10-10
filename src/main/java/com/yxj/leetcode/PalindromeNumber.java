package com.yxj.leetcode;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-08-15 14:37
 */
public class PalindromeNumber {


    public static void main(String[] args) {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        System.out.println(palindromeNumber.isPalindrome(1234321));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        int n = 0;
        int x2 = x;
        while (x > 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }

        return n == x2;

    }

}
