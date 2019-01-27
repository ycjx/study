package com.yxj.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

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
        //往服务端发生消息ycjx
         ctx.writeAndFlush(Unpooled.copiedBuffer("ycjx".getBytes()));
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
        byte[] b = new byte[msg.readableBytes()];
        msg.readBytes(b);
        System.out.println("Cilent received: " + new String(b));
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
