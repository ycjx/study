package com.yxj.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class RadixSort {


    public static int[] arrays = new int[]{23, 34, 45, 12, 34, 32, 13, 85, 32, 4, 1, 54, 24, 74, 25, 86, 65, 38};



    public static void main(String[] args) {
        sort(arrays,10);
        System.out.println(Arrays.toString(arrays));
    }

    public static void sort(int[] array,int index){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(10);
        for(int i=0;i<10;i++){
            list.add(new ArrayList<Integer>());
        }
        int count = 0;
        for(int i=0;i<array.length;i++){
            if((array[i]%(index))/(index/10)>0)
                count++;
            list.get((array[i]%(index))/(index/10)).add(array[i]);
//            try {
//                list.get((array[i]%(index))/(index/10)).add(array[i]);
//            }catch (Exception e){
//                System.out.println((array[i]%(index))/(index/10));
//            }

        }
        int t = 0;
        if (count>0){
            Iterator it = list.iterator();
            while (it.hasNext()){
                ArrayList<Integer> list2 = (ArrayList<Integer>) it.next();
                Iterator it2 = list2.iterator();
                while (it2.hasNext()){
                    array[t++] = (int)it2.next();
                }
            }
            sort(array,index*10);
        }else {
            return;
        }
    }
}
