package com.yxj.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-05-09 11:03
 */
public class ReciveAllDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        Object decoded = decode(ctx, in);
//        if (decoded != null) {
//            out.add(decoded);
//        }
        int i = in.readableBytes();
        if (i > 0) {
            ByteBuf byteBuf = in.readRetainedSlice(i);
            out.add(byteBuf);
        }
    }


//    protected Object decode(
//            @SuppressWarnings("UnusedParameters") ChannelHandlerContext ctx, ByteBuf in) throws Exception {
//
//
//    }
}
