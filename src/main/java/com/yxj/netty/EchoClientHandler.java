package com.yxj.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ByteProcessor;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/1/27 上午12:10
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 当被通知到channel活跃的时候
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //往服务端发生消息

        ByteBuf heapBuffer = Unpooled.buffer(1024);
        heapBuffer.writeBytes("head".getBytes());
        ByteBuf bodyBuffer = Unpooled.buffer(1024);
        bodyBuffer.writeBytes("body".getBytes());

        System.out.println("channelActive");
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        compositeByteBuf.addComponents(true,heapBuffer,bodyBuffer);
         ctx.writeAndFlush(compositeByteBuf);
         super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("注册完毕");
        super.channelRegistered(ctx);
    }

    /**
     * 处理接收到的消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //客户端接受消息可能会分多次接受
        //服务端发送过来的是字节，所以需要将字节数组转换成String


        String str;
        if (msg.isReadable()) {
            byte s = 'y';
            ByteProcessor byteProcessor = new ByteProcessor.IndexOfProcessor(s);
            // 查找buf 中y字节的索引值
            int y =  msg.forEachByte(byteProcessor);

            int readIndex = msg.readerIndex();
            int writeIndex = msg.writerIndex();
            byte[] data = new byte[writeIndex-readIndex];
            msg.readBytes(data,readIndex,writeIndex);
            str = new String(data);
            System.out.println("Cilent received: " + str);


        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
