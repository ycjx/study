package com.yxj.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergerSort {

    public static int[] arrays = new int[]{23, 34, 45, 12, 34, 32, 13, 85, 32, 4, 1, 54, 24, 74, 25, 86, 65, 38};

    public static void main(String[] args) {
        megerSort(arrays, 0, arrays.length - 1);
        System.out.println(Arrays.toString(arrays));

    }

    public static void megerSort(int[] array, int start, int end) {
        if (end - start < 1)
            return;
        int mid = (end + start) / 2;
        megerSort(array, start, mid);
        megerSort(array, mid + 1, end);
        int i = start;
        int j = mid + 1;
        int t = 0;
        int[] copy = new int[array.length + 1];

        while (i <= mid && j <= end) {
            try {
                if (array[i] <= array[j]) {
                    copy[t++] = array[i++];
                }
                if (array[j] < array[i]) {
                    copy[t++] = array[j++];
                }
            } catch (Exception e) {
                System.out.println(start + "---" + end);
                System.out.println(i + "--" + j);
                System.out.println(t);
            }

        }
        while (i <= mid) {
            copy[t++] = array[i++];
        }
        while (j <= end) {

            copy[t++] = array[j++];
        }

        t = 0;
        while (start <= end) {
            array[start++] = copy[t++];
        }

    }
}
