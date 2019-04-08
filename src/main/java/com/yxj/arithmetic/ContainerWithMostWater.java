package com.yxj.arithmetic;

/**
 * @author:yuxj
 * @descriptio 盛最多水的容器
 * @create:2019-03-29 17:04
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        ContainerWithMostWater c = new ContainerWithMostWater();
        System.out.println(c.maxArea(new int[]{0}));
    }

    public int maxArea(int[] height) {
        int max = 0;
        int length = height.length;
        for (int i = 0, j = length - 1; i < j; ) {
            int minHegth = height[i] < height[j] ? height[i++] : height[j--];
            max = Math.max(max, (j - i+1) * minHegth);
        }
        return max;
    }
}
