package com.yxj.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-02-07 13:07
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/ycjx/nginx.conf");
        long length = file.length();
        byte[] ds = new byte[(int) length];
        MappedByteBuffer map = new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY, 0, length);
        for (int offset = 0; offset < length; offset++) {
            byte b = map.get();
            ds[offset] = b;
        }
        Scanner scanner = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
