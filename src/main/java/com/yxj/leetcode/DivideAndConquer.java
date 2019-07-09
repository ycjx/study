package com.yxj.leetcode;

/**
 * @author:yuxj
 * @descriptio   分治算法
 * @create:2018/9/28 下午3:36
 */
public class DivideAndConquer {


    public static void main(String[] args) {
        int[] nums = new int[3];
        nums[0] = 3;
        nums[1] = 2;
        nums[2] = 3;
        System.out.println(majorityElement(nums));
    }


    /**
     * 求众数
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int midIndex = nums.length/2;
        int length = nums.length;

        if(length == 1 || length == 2){
            return nums[0];
        }
        sort(nums);
        return nums[midIndex];
    }

    public  static  int[] sort(int[] array){

        int length = array.length;

        int midIndex = length/2;

        int count = 0;

        while (length>midIndex){
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

    public static void megerSort(int[] array,int start,int end){
        if(end - start<1)
            return;
        int mid = (end+start)/2;
        megerSort(array,start,mid);
        megerSort(array,mid+1,end);
        int i = start;
        int j = mid+1;
        int t = 0;
        int[] copy = new int[array.length+1];

        while (i<=mid&&j<=end){
            if(array[i]<=array[j]){
                copy[t++] = array[i++];
            }
            if(array[j]<array[i]){
                copy[t++] = array[j++];
            }

        }
        while (i<=mid){
            copy[t++] = array[i++];
        }
        while (j<=end){

            copy[t++] = array[j++];
        }

        t=0;
        while (start<=end){
            array[start++] = copy[t++];
        }

    }
}
