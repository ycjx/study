package com.yxj.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 分为多个数组插入
 * 时间复杂度 平均O(NlogN)   最好O(NlogN) 最坏O(N2)
 */
public class ShellSort {

    public static int[] array = new int[]{23, 34, 45, 12, 34, 32, 13, 85, 32, 4, 1, 54, 24, 74, 25, 86, 65, 38};

    public static void main(String[] args) {

        array = sort(array);

        System.out.println(Arrays.toString(array));

    }

    public static int[] sort(int[] array) {
        int lenght = array.length;
        int d = lenght / 2;
        while (d != 0) {
            for (int i = d; i < lenght; i++) {
                int temp = array[i];
                int j = i - d;
                while (j >= 0 && temp < array[j]) {
                    array[j + d] = array[j];
                    j = j - d;
                }
                array[j + d] = temp;

            }
            d = d / 2;

        }
        return array;
    }
}
