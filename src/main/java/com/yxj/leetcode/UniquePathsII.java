package com.yxj.leetcode;

/**
 * @author:ycjx
 * @descriptio 不同路径 II
 * @create:2019-10-28 14:55
 */
public class UniquePathsII {

    public static void main(String[] args) {
        int[][] s = new int[3][3];
        s[0][0] = 0;
        s[0][1] = 0;
        s[0][2] = 0;
        s[1][0] = 0;
        s[1][1] = 1;
        s[1][2] = 0;

        s[2][0] = 0;
        s[2][1] = 0;
        s[2][2] = 0;

//        int[][] s = new int[1][1];
//        s[0][0] = 0;


        UniquePathsII uniquePathsII = new UniquePathsII();
        System.out.println(uniquePathsII.uniquePathsWithObstacles(s));


    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int I = obstacleGrid.length;
        int J = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        } else {
            obstacleGrid[0][0] = 1;
        }
        for (int i = 1; i < I; i++) {
            if (obstacleGrid[i][0] == 1) {
                obstacleGrid[i][0] = 0;
            } else {
                obstacleGrid[i][0] = obstacleGrid[i - 1][0];
            }
        }
        for (int j = 1; j < J; j++) {
            if (obstacleGrid[0][j] == 1) {
                obstacleGrid[0][j] = 0;
            } else {
                obstacleGrid[0][j] = obstacleGrid[0][j - 1];
            }
        }
        for (int i = 1; i < I; i++) {
            for (int j = 1; j < J; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }
        return obstacleGrid[I - 1][J - 1];
    }


}
