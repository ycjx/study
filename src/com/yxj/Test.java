package com.yxj;



public class Test {

    public static void main(String[] args) {
//        Com();

        String a = new String("a");
        a.intern();

        String b = "a";
        a.intern();

//        System.out.println(a.equals());

    }


    public static boolean Com(){
        int i=1;
        return  i==1?true:false;
    }
}
