package com.yxj.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/2/14 下午10:27
 */
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {


    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) {
        out.add(String.valueOf(msg));

    }
}
