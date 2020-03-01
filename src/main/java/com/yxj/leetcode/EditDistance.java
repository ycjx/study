package com.yxj.leetcode;

/**
 * @author:ycjx
 * @descriptio 编辑距离
 * @create:2019-10-31 10:03
 */
public class EditDistance {

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance("",""));

    }


    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= length1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= length2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }

                if (dp[i][j] > dp[i - 1][j]+1) {
                    dp[i][j] = dp[i - 1][j]+1;
                }
                if (dp[i][j] > dp[i][j - 1]+1) {
                    dp[i][j] = dp[i][j - 1]+1;
                }
            }
        }
        return dp[length1][length2];

    }
}
