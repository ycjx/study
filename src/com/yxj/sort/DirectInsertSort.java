package com.yxj.sort;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 直接插入排序
 * 第一第二个组个一个排序,将第三个插入组成一个新的排序
 * 时间复杂度 平均O(N2)  最好O(N)  最坏O(N2)
 *
 */
public class DirectInsertSort {

    public static  int[] array = new int[]{23,34,45,12,34,32,13,85,32,4,1,54,24,74,25,86,65,38};

    public static void main(String[] args) {

        array = sort(array);

        System.out.println(Arrays.toString(array));
    }

    public static int[] sort(int[] array){

        int length = array.length; //数组长度
        for(int i=1;i<array.length;i++){
            int tmp = array[i]; //想要移动的数
            int j = i-1;
            while (j>=0&&array[j]>tmp){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = tmp;
        }

        return  array;
    }
}
