package com.yxj;


import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Test {


    public static void main(String[] args) throws Exception {
        int i = 10;
        Random random = new Random();
        System.out.println(random.nextDouble());


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
