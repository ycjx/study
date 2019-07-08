package com.yxj.thread;


//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Data
public class Demo2 {


    private Integer a;


    public static void main(String[] args) throws Exception {


        Station s = new Station();

        Field ss = null;

        try {
            ss = s.getClass().getDeclaredField("tricketsSoldss");
        } catch (Exception var3) {

        }
        Field f = null;
        try {
            f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        } catch (Exception var3) {

        }

        f.setAccessible(true);

        Unsafe unsafe = (sun.misc.Unsafe) f.get((Object) null);


        Demo2 demo2 = new Demo2();

//        System.out.println(unsafe.objectFieldOffset(ss));

        int[] a = new int[3];
        int i = 0;
        unsafe.putOrderedObject(a, 19, 1);
//        System.out.println(a[0]);
//        System.out.println(a[1]);
//        System.out.println(a[2]);

        int scale = unsafe.arrayIndexScale(Demo2[].class);
        int ashit = 31 - Integer.numberOfLeadingZeros(scale);


        System.out.println(unsafe.arrayBaseOffset(Demo2[].class));
        System.out.println(unsafe.arrayIndexScale(Demo2[].class));

        System.out.println(ashit);


    }
}
