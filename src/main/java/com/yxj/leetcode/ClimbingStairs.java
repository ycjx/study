package com.yxj.leetcode;

/**
 * @author:ycjx
 * @descriptio 爬楼梯
 * @create:2019-10-10 17:57
 */
public class ClimbingStairs {

    public static void main(String[] args) {

        ClimbingStairs climbingStairs = new ClimbingStairs();
        Long time1 = System.currentTimeMillis();
        System.out.println(climbingStairs.climbStairs2(3));
        System.out.println(climbingStairs.climbStairs2(4));
        System.out.println(climbingStairs.climbStairs2(5));
        System.out.println(climbingStairs.climbStairs2(6));
        System.out.println(climbingStairs.climbStairs2(7));
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairs2(int n) {
        if(n==1){
            return 1;
        }
        if(n == 2){
            return 2;
        }

        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for (int i = 3; i <= n; i++) {
            nums[i - 1] = nums[i - 2] + nums[i - 3];
        }
        return nums[n - 1];
    }
}
