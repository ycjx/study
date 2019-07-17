package com.yxj.leetcode;

/**
 * @author:ycjx
 * @descriptio 跳跃游戏II
 * @create:2019-07-10 10:31
 */
public class JumpGameII {

    public static void main(String[] args) {
        JumpGameII jumpGameII = new JumpGameII();
        int[] jum = new int[]{0, 0, 0, 0, 4};
        System.out.println(jumpGameII.jump(jum));
    }

    public int jump(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }
        int[] position = new int[length];
        position[0] = 0;
        for (int i = 1; i < length; i++) {
            position[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < length; i++) {
            int size = nums[i];
            int value = position[i];
            for (int j = i + 1; j <= size + i && j < length; j++) {
                if (position[j] > value + 1) {
                    position[j] = value + 1;
                }
            }
        }
        return position[length - 1];

    }


    /**
     * 别人的答案
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        if(nums.length == 1) return 0;
        int reach = 0;
        int nextreach = nums[0];
        int step = 0;
        for(int i = 0;i<nums.length;i++){
            nextreach = Math.max(i+nums[i],nextreach);
            if(nextreach >= nums.length-1) return (step+1);
            if(i == reach){
                step++;
                reach = nextreach;
            }
        }
        return step;
    }

}
