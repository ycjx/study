package com.yxj.arithmetic;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

/**
 * @author:yuxj
 * @descriptio
 * @create:2018/9/28 下午3:22
 */
public class DI {


    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("asda"));
        FileChannel c = fileInputStream.getChannel();

    }


    public int numPermsDISequence(String s) {
        char[] di = s.toCharArray();
        return 0;
    }


    public int majorityElement(int[] nums) {
        return 0;
    }
}
