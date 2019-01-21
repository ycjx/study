package com.yxj;

import java.util.Arrays;

/**
 * 二叉堆
 */
public class Binaryheap<E extends Comparable<? super E>>  {

    private int currentSize; //二叉堆size

    private E[] array;

    public boolean isEmpty(){
        if(array[1] == null){
            return true;
        }
        return false;
    }

    /**
     * 插入元素
     * @param x
     */
    public void insert(E x){

        //当前数组插入x后是否会超过长度
        if(currentSize == array.length-1){
            enlargeArray(array.length*2+1);
        }
        int inSertedSize = ++currentSize;
        //依次往父级比较 比x大的父级往下放
        while(array[inSertedSize/2].compareTo(x)<0){
            array[currentSize] = array[currentSize/2];
            inSertedSize = inSertedSize/2;
        }
        array[inSertedSize] = x;


    }

    public E[] enlargeArray(int newSise){
        if(newSise<array.length+1){
            throw new IndexOutOfBoundsException("新数组的长度小于当前数组长度");

        }
//        E[] newArray = (E[]) new Object[newSise];
        return Arrays.copyOf(array,newSise);
    }
}
