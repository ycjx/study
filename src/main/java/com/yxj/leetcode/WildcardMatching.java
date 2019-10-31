package com.yxj.leetcode;

/**
 * @author:ycjx
 * @descriptio 通配符匹配
 * @create:2019-10-28 11:21
 */
public class WildcardMatching {


    public static void main(String[] args) {
        String s = "asd";

        String p = "???";
        WildcardMatching wildcardMatching = new WildcardMatching();
        System.out.println(wildcardMatching.isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        int pLength = p.length();
        int sLength = s.length();
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        boolean[][] dp = new boolean[sLength + 1][pLength + 1];
        dp[0][0] = true;
        for (int j = 1; j < pLength + 1; j++) {
            if (pChars[j - 1] == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i < sLength + 1; i++) {
            for (int j = 1; j < pLength + 1; j++) {
                if (sChars[i - 1] == pChars[j - 1] || pChars[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChars[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[sLength][pLength];


    }


}
