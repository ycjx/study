package com.yxj.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuxj
 * @descriptio 括号生成
 * @create:2019-04-17 22:13
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        System.out.println(generateParentheses.generateParenthesis(1));
    }


    public List<String> generateParenthesis(int n) {
        List<String> s = new ArrayList<>();
        result("(",1,1,0,n,s);
        return s;
    }


    public void result(String s, int num, int leftNum, int rightNum, int n, List<String> list) {
        if (rightNum > leftNum) {
            return;
        }
        if (leftNum > n) {
            return;
        }
        if(2*n == num){
            list.add(s);
            return;
        }
        String left = s + "(";
        result(left, ++num, ++leftNum, rightNum, n, list);
        String right = s + ")";
        result(right, num, --leftNum, ++rightNum, n, list);
    }

}
