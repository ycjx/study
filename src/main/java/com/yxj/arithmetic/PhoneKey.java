package com.yxj.arithmetic;

import java.util.*;

/**
 * @author:yuxj
 * @descriptio
 * @create:2018/10/13 下午3:29
 */
public class PhoneKey {

    public static void main(String[] args) {

        String[] chars = {"3","1","0"};
        List<List<String>>  list = new ArrayList<>();

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                for(int k=0;k<3;k++){
                    for(int l=0;l<3;l++){
                        List<String> key = new ArrayList<>();

                        key.add(chars[i]);
                        key.add(chars[j]);
                        key.add(chars[k]);
                        key.add(chars[l]);
                        list.add(key);
                    }
                }

            }
        }

        List<List<String>>  keys = new ArrayList<>();

        for(List<String> list1 : list){

            HashSet h = new HashSet(list1);
            if(h.size() == 3){
                keys.add(list1);
            }
        }

        for(List<String> list1 : keys){
            System.out.println(list1.toString());
        }

    }

}
