package com.yxj.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019/1/21 下午10:31
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {


        int port = 8002;
        new EchoServer(port).start();

    }

    public void start() throws Exception {
        final EchoServerInHandler serverInHandler = new EchoServerInHandler();
        final EchoServerOutHandler serverOutHandler = new EchoServerOutHandler();
        EventLoopGroup group = new NioEventLoopGroup(1);
        EventLoopGroup group2 = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group, group2)
                    .channel(NioServerSocketChannel.class) //指定所使用的channel Epoll 只支持在linux 比nio更快 ,完全非阻塞
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(new ReciveAllDecoder())
                                    .addLast(serverOutHandler)  //TODO 为什么出站的handle要在入站handler前面
                                    .addLast(serverInHandler);
                        }
                    });
            //异步的绑定到服务器，调用sync方法阻塞等到绑定完成
            ChannelFuture f = b.bind().sync();
            //获取channel的closeFuture,并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } catch (Exception ex) {

        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
