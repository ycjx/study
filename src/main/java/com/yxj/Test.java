package com.yxj;


import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Test {


    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        int i = 0;
        int size = list.size();
        while (i < size) {
            int j = i;
            i = i+4>size?size:i+4;
            List<Integer> list1 = list.subList(j, i);
            System.out.println(list1);
        }
//
//        int i =0;
//        List<Integer> child = new ArrayList<>();
//        child = list.subList(i,i+=5);
//        do {
//            System.out.println(child.toString());
//            child = list.subList(i,i+=5);
//        }  while (CollectionUtils.isEmpty(child));


    }


    /**
     * 获取具体时间的几个月后的某天
     *
     * @param date 毫秒
     * @return
     */
    public static Long getDayByIndex(Long date, int index) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date);
        calendar1.add(Calendar.DAY_OF_YEAR, index);
//            calendar1.set(Calendar.DAY_OF_MONTH,calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(calendar1.getTime());
        System.out.println(sdf.format(calendar1.getTime()));
        return sdf.parse(sdf.format(calendar1.getTime())).getTime() / 1000;
    }


}
