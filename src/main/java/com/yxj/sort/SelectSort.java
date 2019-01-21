package com.yxj.sort;


import java.util.Arrays;

/**
 * 选择排序
 * 每次将最小的放在最前面
 */
public class SelectSort {

    public static  int[] array = new int[]{23,34,45,12,34,32,13,85,32,4,1,54,24,74,25,86,65,38};

    public static void main(String[] args) {
        array = sort(array);

        System.out.println(Arrays.toString(array));
    }

    public static int[] sort(int[] array){

        int length = array.length;

        for(int i=0;i<length-1;i++){

            int position = i;
            for(int j=i+1;j<length;j++){
                if(array[position] > array[j]){
                    position = j;
                }
            }
            if(position > i){
                int tmp = array[i];
                array[i] = array[position];
                array[position] = tmp;
            }
        }

        return  array;
    }
}
