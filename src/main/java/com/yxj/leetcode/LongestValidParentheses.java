package com.yxj.leetcode;

/**
 * @author:ycjx
 * @descriptio 最长有效括号
 * @create:2019-10-25 16:29
 */
public class LongestValidParentheses {


    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses("(()"));
    }


    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 1) {
            return 0;
        }
        int length = s.length();
        char[] chars = s.toCharArray();

        int maxNum = Math.max(cla(chars, '(', 0, length, 1), cla(chars, ')', length - 1, -1, -1));
        return maxNum;
    }


    public int cla(char[] chars, char temp, int i, int length, int flag) {
        int maxNum = 0;
        int num = 0;
        int tempNum = 0;
        int validLen = 0;

        for (; i != length; i += flag) {
            if (temp == chars[i]) {
                tempNum++;
            } else {
                tempNum--;
            }
            num++;
            if (tempNum < 0) {
                maxNum = validLen>maxNum?validLen:maxNum;
                num = 0;
                tempNum = 0;
            } else if (tempNum == 0) {
                validLen = num;
            }
        }
        if (validLen > maxNum) {
            maxNum = validLen;
        }
        return maxNum;
    }
}
