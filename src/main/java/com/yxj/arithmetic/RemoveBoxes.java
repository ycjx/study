package com.yxj.arithmetic;

/**
 * @author:yuxj
 * @descriptio 移除盒子
 * @create:2019-04-05 15:57
 */
public class RemoveBoxes {

    public static void main(String[] args) {
        int[] s = new int[5];
        s[0] = 5;
        s[1] = 6;
        s[2] = 6;
        s[3] = 7;
        s[4] = 5;
        RemoveBoxes removeBoxes = new RemoveBoxes();
        System.out.println(removeBoxes.removeBoxes(s));
    }


    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return removeBoxesSub(boxes, 0, n - 1, 0, dp);
    }

    /**
     *  https://blog.csdn.net/STILLxjy/article/details/85106608 解题思路
     * @param boxes
     * @param i
     * @param j
     * @param k
     * @param dp
     * @return
     */
    private int removeBoxesSub(int[] boxes,int i,int j,int k,int[][][] dp){
        if (i > j) return 0;
        if (dp[i][j][k] > 0) return dp[i][j][k];

        int res = (k + 1) * (k + 1) + removeBoxesSub(boxes, i + 1, j, 0, dp);

        for (int m = i + 1; m <= j; m++) {
            if (boxes[i] == boxes[m]) {
                res = Math.max(res, removeBoxesSub(boxes, i + 1, m - 1, 0, dp) + removeBoxesSub(boxes, m, j, k + 1, dp));
            }
        }

        dp[i][j][k] = res;
        return res;
    }
}
