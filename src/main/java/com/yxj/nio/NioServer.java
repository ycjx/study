package com.yxj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {


    /*接受数据缓冲区*/
    private ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
    /*发送数据缓冲区*/
    private ByteBuffer receivebuffer = ByteBuffer.allocate(1024);

    private Selector selector;


    public ExecutorService executorService = Executors.newFixedThreadPool(10);


    public NioServer(int port) throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 检索与此通道关联的服务器套接字
        ServerSocket serverSocket = serverSocketChannel.socket();
        // 进行服务的绑定
        serverSocket.bind(new InetSocketAddress(port));
        // 通过open()方法找到Selector
        selector = Selector.open();
        // 注册到selector，等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server Start----:");


    }

    //
    private void listen() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handleAccept(selectionKey);
            }
        }
    }

    private void handleAccept(SelectionKey selectionKey) throws IOException {
        // 接受请求
        ServerSocketChannel socketChannel;
        SocketChannel channel;
        int count = 0;
        if (selectionKey.isAcceptable()) {
            // 返回为之创建此键的通道。
            socketChannel = (ServerSocketChannel) selectionKey.channel();
            // 接受到此通道套接字的连接。
            // 此方法返回的套接字通道（如果有）将处于阻塞模式。
            channel = socketChannel.accept();
            // 配置为非阻塞
            channel.configureBlocking(false);

            //分发任务
            Dispather dispather = new Dispather();
            dispather.register(channel);
            //提交到线程池
            executorService.execute(dispather);
        }

    }


    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int port = 8080;
        NioServer server = new NioServer(port);
        server.listen();
    }


    class Dispather implements Runnable {

        private Selector selector;

        public Dispather() throws IOException {
            init();
        }

        private void init() throws IOException {
            this.selector = SelectorProvider.provider().openSelector();
        }

        public void register(SocketChannel channel) throws IOException {
            channel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        // 返回为之创建此键的通道。
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        if (selectionKey.isReadable()) {
                            //将缓冲区清空以备下次读取
                            receivebuffer.clear();
                            //读取服务器发送来的数据到缓冲区中
                            int count = channel.read(receivebuffer);
                            if (count > 0) {
                                String receiveText = new String(receivebuffer.array(), 0, count);
                                System.out.println("服务器端接受客户端数据--:" + receiveText);
                                channel.register(selector, SelectionKey.OP_WRITE);
                            }
                        } else if (selectionKey.isWritable()) {
                            //将缓冲区清空以备下次写入
                            sendbuffer.clear();
                            // 返回为之创建此键的通道。
                            channel = (SocketChannel) selectionKey.channel();
                            String sendText = "message from server--";
                            //向缓冲区中输入数据
                            sendbuffer.put(sendText.getBytes());
                            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
                            sendbuffer.flip();
                            //输出到通道
                            channel.write(sendbuffer);
                            System.out.println("服务器端向客户端发送数据--：" + sendText);
                            channel.register(selector, SelectionKey.OP_READ);
                        }
                    }
                } catch (IOException ex) {

                }

            }
        }
    }

}
