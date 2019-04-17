package com.yxj.arithmetic;

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
        StringBuilder a = new StringBuilder();
        a.append("(");
        result(a, 1, 1, 0, n, s);
        return s;
    }


    public void result(StringBuilder s, int num, int leftNum, int rightNum, int n, List<String> list) {
        if (rightNum > leftNum) {
            return;
        }
        if (leftNum > n) {
            return;
        }
        if (2 * n == num) {
            list.add(s.toString());
            return;
        }
        StringBuilder left = s.append("(");
        result(left, ++num, ++leftNum, rightNum, n, list);
        StringBuilder right = s.append(")");
        result(right, num, --leftNum, ++rightNum, n, list);
    }

}
