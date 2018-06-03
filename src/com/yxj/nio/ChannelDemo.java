package com.yxj.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {
    private static final byte message[] = { 83, 111, 109, 101, 32,
            98, 121, 116, 101, 115, 46 };

    public static void main(String[] args) throws  Exception{

//        byteBufferRead();
        ByteBufferWrite();

        try{

        }catch (IndexOutOfBoundsException e){

        }
        System.out.println("sss");





    }

    public static void byteBufferRead() throws  Exception{
        FileInputStream in = new FileInputStream("H:\\动漫\\阿里云密码.txt");

        //获取通道
        FileChannel ch = in.getChannel();

        //设置buff长度 1024为容量（限制位置） 当前位置
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //buffer 填充数据 当前位置往后递增数据长度
        ch.read(buffer);

        //将限制位置设置为当前位置 当前位置设置为0
        buffer.flip();

        //查询当前位置和限制位置是否有数据
        while (buffer.remaining()>0){
            byte b = buffer.get();
            System.out.println((char)b);
        }

        in.close();
    }

    public static void ByteBufferWrite(){

//        FileOutputStream out = new FileOutputStream("H:\\动漫\\test.txt");
//
//        FileChannel ch = out.getChannel();
//
//        ByteBuffer bf = ByteBuffer.allocate(1024);
//
//        //将数据写入到buff
//        for (int i=0; i<message.length; ++i) {
//            bf.put( message[i] );
//        }
//
//        //同上
//        bf.flip();
//
//        ch.write(bf);
//
//        out.close();

        throw new IndexOutOfBoundsException("sds");
    }
}
