package com.yxj.sort;

import java.util.Arrays;


/**
 * 冒泡排序
 */
public class BubbleSort {

    public static int[] array = new int[]{23, 34, 45, 12, 34, 32, 13, 85, 32, 4, 1, 54, 24, 74, 25, 86, 65, 38};

    public static void main(String[] args) {

        array = sort(array);

        System.out.println(Arrays.toString(array));
    }

    public static int[] sort(int[] array) {

        int length = array.length;

        for (int i = 0; i < length; i++) {
            boolean flag = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            //当发现没有需要交换时 说明排序完成
            if (!flag) {
                break;
            }
        }

        return array;
    }
}
