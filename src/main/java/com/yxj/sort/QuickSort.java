package com.yxj.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 小的放左边 大的右边
 */
public class QuickSort {

    public static int[] arrays = new int[]{23, 34, 45, 12, 34, 32, 13, 85, 32, 4, 1, 54, 24, 74, 25, 86, 65, 38};

    public static void main(String[] args) {
        sort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    public static void sort(int[] array) {
        qkSort(array, 0, array.length - 1);
    }

    public static void qkSort(int[] numbers, int start, int end) {
        if(start>end)
            return;
        int base = numbers[start]; // 选定的基准值（第一个数值作为基准值
        int temp; // 记录临时中间值
        int i = start, j = end;
        while (i < j) {

            while ((i<j) &&(numbers[j] >=base)  )
                j--;
            if (i<j)
                numbers[i++] = numbers[j];
            while ((i < j)&&(numbers[i] <= base))
                i++;
            if (i<j)
                numbers[j--] = numbers[i];

        }
        numbers[i] = base;
        qkSort(numbers, start, i-1);
        qkSort(numbers, i+1, end);
    }
}


