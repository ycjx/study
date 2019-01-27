package com.yxj.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/1/27 下午4:17
 */
public class EchoServerOutHandler extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        System.out.println("即将出站的消息" + msg.toString());

        super.write(ctx, msg, promise);
    }
}
