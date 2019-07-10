package com.yxj.leetcode;

/**
 * @author:yuxj
 * @descriptio 接雨水
 * @create:2019-05-07 17:05
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int[] water = new int[]{2, 0, 2};
        System.out.println(trappingRainWater.trap(water));
    }


    public int trap(int[] height) {
        int length = height.length;
        if (length < 3) {
            return 0;
        }
        int head = 0;
        int tail = length;
        int leftLength = height[0];
        int rightLength = height[length - 1];
        int value = 0;
        for (int i = 1; i < length; i++) {
            if (height[i] >= leftLength) {
                leftLength = height[i];
                head = i;
            } else {
                break;
            }
        }
        for (int i = length - 2; i >= 0; i--) {
            if (height[i] >= rightLength) {
                rightLength = height[i];
                tail = i;
            } else {
                break;
            }
        }
        while (head < tail ) {
            if (leftLength <= rightLength) {
                head++;
                if (head >= length) {
                    break;
                }
                if (height[head] >= leftLength) {
                    leftLength = height[head];
                } else {
                    value = value + (leftLength - height[head]);
                }
            } else {
                tail--;
                if (tail < 0) {
                    break;
                }
                if (height[tail] > rightLength) {
                    rightLength = height[tail];
                } else {
                    value = value + (rightLength - height[tail]);
                }
            }


        }
        return value;
    }
}
