package com.yxj;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {

    public static void main(String[] args) throws Exception {
        String key = "adadada-sadada";
        int keySize = key.lastIndexOf("-");
        key = key.substring(0,keySize);
        System.out.println(key);

    }


    public static String parseAmountFormat(BigDecimal amount) {
        Integer amountInt = amount.intValue();
        if (amountInt < 0) {
            amount = amount.multiply(new BigDecimal(-1));
            return "-¥" + amount.divide(new BigDecimal(100)).setScale(2).toString();
        } else {
            return "¥" + amount.divide(new BigDecimal(100)).setScale(2).toString();
        }
    }

    public static Integer test(Integer a) {
        a = a + 20;
        return a;
    }


    private static long getTomorrowTime()  throws Exception {
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);
            Date date1 = formatter.parse(dateString);
            //获取第二天00：00时的毫秒值
            long time1 = date1.getTime();
            //获取当前毫秒值
            long nowTime = System.currentTimeMillis();
            //返回差值
            return time1 - nowTime;

    }
}
