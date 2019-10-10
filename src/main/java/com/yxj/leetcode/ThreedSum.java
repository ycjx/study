package com.yxj.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-08-15 17:33
 */
public class ThreedSum {


    public static void main(String[] args) {
        ThreedSum threedSum = new ThreedSum();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threedSum.threeSum(nums);
        System.out.println(lists.toString());
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        if (length < 4) {
            return lists;
        }
        Arrays.sort(nums);
        int i = 0, j = length - 1;
        while ((nums[i] < 0 || nums[j] > 0) && i < j) {
            boolean isExist = false;
            for (int k = i + 1; k < j; k++) {
                if ((nums[i] + nums[j] + nums[k]) == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[k]);
                    list.add(nums[j]);
                    lists.add(list);
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                int i1 = i + 1;
                int j1 = j - 1;
//                while (i1 < ]==) {
//
//                }
            }
            if (true) {
                i = i + 1;
                while (i < length && nums[i] == nums[i - 1]) {
                    i = i + 1;
                    System.out.println("i:" + i);
                }
                j = j - 1;
                while (j >= 0 && nums[j + 1] == nums[j]) {
                    System.out.println("j:" + j);
                }
                System.out.println("i:" + i + "------j:" + j);
            } else {

            }


        }
        return lists;

        ///   -4 ,-1 ,-1 ,0,1,2

    }
}
