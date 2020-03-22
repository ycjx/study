package com.yxj;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        String a = new StringBuilder("dadadasdadadadadada").toString().intern();
        String b = new String("dadadasdadadadadada");
        System.out.println(a.intern() == b);

        System.out.println(3 == 1*3);

        System.out.println(0.1*3);
        int n = 1;

        switch (n) {
            case 2:
                System.out.println(2);
            case 1:
                System.out.println(1);
                break;
            default:
                System.out.println(3);
        }




    }


    public static void updateManualSingletonNames(Consumer<Set<String>> action, Predicate<Set<String>> condition) {
        Set<String> s = new HashSet<>();
        action.accept(s);

        condition.test(s);

    }


}
