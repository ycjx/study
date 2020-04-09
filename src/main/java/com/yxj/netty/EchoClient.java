package com.yxj.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/1/27 上午1:01
 */
public class EchoClient {

    private final String host;

    private final int port;

    private final Bootstrap b;

    public Bootstrap getB() {
        return b;
    }


    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.b = new Bootstrap();
    }


    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new LineBasedFrameDecoder(7))
                                    .addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.addListener(lis -> {
                if (lis.isSuccess()) {
                    Channel channel = ((ChannelFuture) lis).channel();
                    ByteBuf heapBuffer2 = Unpooled.buffer(1024);
                    heapBuffer2.writeBytes("qqqqqqq\n cccccccccccc".getBytes());
                    f.channel().writeAndFlush(heapBuffer2);
                }
            });
            ByteBuf heapBuffer = Unpooled.buffer(1024);
            heapBuffer.writeBytes("Hello Netty Server, I am a\n common cli\nent".getBytes());
            f.channel().writeAndFlush(heapBuffer);
//            Thread.sleep(1000);

            new Thread(() -> {
                ByteBuf heapBuffer2 = Unpooled.buffer(1024);
                heapBuffer2.writeBytes("12313131313\n common client".getBytes());
                f.channel().writeAndFlush(heapBuffer2);
            }).start();

            f.channel().closeFuture().sync();
        } catch (Exception ex) {

        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: host+port");
        }

        String host = "127.0.0.1";
        int port = 8002;
        EchoClient echoClient = new EchoClient(host, port);

        echoClient.start();

        Thread.sleep(2000);


        ChannelFuture f2 = echoClient.getB().connect().sync();

        ByteBuf heapBuffer = Unpooled.buffer(1024);
        heapBuffer.writeBytes("这是第二个".getBytes());
        System.out.println("这是第二个");
        f2.channel().writeAndFlush(heapBuffer);
        f2.channel().closeFuture().sync();
    }

}
