package com.yxj.sort;


import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {

    public static  int[] array = new int[]{23,34,45,12,34,32,13,85,32,4,1,54,24,74,25,86,65,38};

    public static void main(String[] args) {


        array = sort(array);

        System.out.println(Arrays.toString(array));

    }

    public  static  int[] sort(int[] array){

        int length = array.length;

        int count = 0;

        while (length>0){
            for(int i= length/2;i>0;i--){

                if(i*2<length&&array[i*2-1+count]>array[i*2+count]){
                    int tmp = array[i*2-1+count];
                    array[i*2-1+count] = array[i*2+count];
                    array[i*2+count] = tmp;
                }
                if(array[i-1+count]>array[i*2-1+count]){
                    int tmp = array[i-1+count];
                    array[i-1+count] = array[i*2-1+count];
                    array[i*2-1+count] = tmp;
                }
            }
            count++;
            length--;
        }



        return  array;
    }

}
