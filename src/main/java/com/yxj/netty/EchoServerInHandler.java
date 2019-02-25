package com.yxj.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/1/21 下午9:32
 */
@ChannelHandler.Sharable
public class EchoServerInHandler extends ChannelInboundHandlerAdapter {

    ChannelFuture connectFuture;

//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        super.channelRegistered(ctx);
//    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.channel(NioSocketChannel.class)
//                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
//                    @Override
//                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
//                        System.out.println("received data");
//                    }
//                });
        //TODO 尽可能重用EventLoop 不是很理解
//        bootstrap.group(ctx.channel().eventLoop());
//        connectFuture  = bootstrap.connect(new InetSocketAddress("www.baidu.com",80));
    }

    /**
     * 处理接收到的消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        String str;
        if (in.isReadable()) {
            int readIndex = in.readerIndex();
            int writeIndex = in.writerIndex();
            byte[] data = new byte[writeIndex-readIndex];
            in.readBytes(data,readIndex,writeIndex);
            str = new String(data);
            System.out.println("服务器接收：" + str);

        }

        ByteBuf heapBuffer = Unpooled.buffer(1024);
        heapBuffer.writeBytes("yay".getBytes());
        ByteBuf bodyBuffer = Unpooled.buffer(1024);
        bodyBuffer.writeBytes("body".getBytes());


        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        //TODO 找个时间研究下为什么 increaseWriterIndex 为false时客户端就是不到，貌似还堵塞住了
        compositeByteBuf.addComponents(true, bodyBuffer, heapBuffer);
        //TODO 为什么写入String类型 客户端接收不到
        ctx.writeAndFlush(compositeByteBuf);
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("readComplete");
//        ctx.writeAndFlush("yay1")
//                .addListener(ChannelFutureListener.CLOSE);
////        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
////                .addListener(ChannelFutureListener.CLOSE);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
