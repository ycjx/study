package com.yxj;


import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Test {


    private static final int  RSLOCK     = 1;
    private static final int  RSIGNAL    = 1 << 1;
    private static final int  STARTED    = 1 << 2;
    private static final int  STOP       = 1 << 29;
    private static final int  TERMINATED = 1 << 30;
    private static final int  SHUTDOWN   = 1 << 31;

    public static void main(String[] args) throws Exception {
        System.out.println(12313 & 0xfffe );
        System.out.println(RSLOCK );
        System.out.println(RSIGNAL );
        System.out.println(STARTED );
        System.out.println(STOP );
        System.out.println(TERMINATED );
        System.out.println(SHUTDOWN );
        System.out.println(5&4);


        int scale = 10;

        int ashit = Integer.numberOfLeadingZeros(10);
        System.out.println(ashit);

        int j = ((8191 & 4096) << 2) + 16;
        System.out.println(j);


    }


    public static void updateManualSingletonNames(Consumer<Set<String>> action, Predicate<Set<String>> condition) {
        Set<String> s = new HashSet<>();
        action.accept(s);

        condition.test(s);

    }


}
