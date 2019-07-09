package com.yxj.leetcode;

/**
 * @author:yuxj
 * @descriptio 跳跃游戏
 * @create:2019-04-12 11:42
 */
public class JumpGame {


    public static void main(String[] args) {
        int[] a = new int[]{0,2,3};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump2(a));
    }

    public boolean canJump(int[] nums) {
        int length = nums.length;
        int[] a = new int[length];
        a[0] = 1;
        for (int i = 0; i < length; i++) {
            int k = nums[i];
            if(a[i]==1){
                for (int j = i+1; j < length && j < i + k+1; j++) {
                    a[j] = 1;
                }
                if (a[length-1] == 1) {
                    break;
                }
            }

        }
        return a[length-1] == 1;
    }

    public boolean canJump2(int[] nums) {
        int length = nums.length;
        int result = length-1;
        for(int i=result;i>=0;--i){
            if(nums[i]+i>=result)
                result = i;
        }
        return result == 0;
    }





}
