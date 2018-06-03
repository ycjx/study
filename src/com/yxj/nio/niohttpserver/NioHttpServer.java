package com.yxj.nio.niohttpserver;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class NioHttpServer implements Runnable {

    public static void main(String[] args) throws IOException {
        String root = new File(".").getAbsolutePath();
        int port = 8080;
        if(args.length >0){
            port = Integer.parseInt(args[0]);
        }
        if(args.length > 1)
            root = args[1];

        NioHttpServer server = new NioHttpServer(null,port);
        int cpu = Runtime.getRuntime().availableProcessors();

        ButterflySoftCache cache = new ButterflySoftCache();
         for(int i=0; i<cpu;i++){
             RequestHandler handler = new RequestHandler(server,root,cache);
             server.addRequestHandler(handler);
             new Thread(handler,"worker"+i).start();
         }

         new Thread(server,"selector").start();


    }


    private ServerSocketChannel serverChannel;
    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(8912);

    private List<ChangeRequest> changeRequests = new LinkedList<ChangeRequest>();

    private Map<SocketChannel,List<ByteBuffer>> pendingSent =new HashMap<SocketChannel,List<ByteBuffer>>();

    private List<RequestHandler> requestHandlers = new ArrayList<RequestHandler>();

    public NioHttpServer(InetAddress address,int port) throws IOException {
        selector = Selector.open();
        serverChannel  = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(address,port));
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void accept(SelectionKey key) throws IOException {
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ);
    }

    public void addRequestHandler(RequestHandler handler){
        requestHandlers.add(handler);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        readBuffer.clear();
        int numRead;
        try{
            numRead = socketChannel.read(readBuffer);
        }catch (IOException e){
            key.cancel();
            socketChannel.close();
            return;
        }
        if(numRead == -1){
            socketChannel.close();
            key.cancel();
            return;
        }

        int worker = socketChannel.hashCode() % requestHandlers.size();
        requestHandlers.get(worker).processData(socketChannel,readBuffer.array(),numRead);
    }

    @Override
    public void run() {
        SelectionKey key = null;
        while(true){
            try {
                synchronized (changeRequests){
                    for(ChangeRequest request : changeRequests){
                        switch (request.type){
                            case ChangeRequest.CHANGOPS:
                                key = request.socket.keyFor(selector);
                                if(key != null && key.isValid()){

                                    key.interestOps(request.ops);
                                }
                                break;
                        }

                    }
                    changeRequests.clear();
                }

                selector.select();
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                while(selectionKeyIterator.hasNext()){
                    key = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    if(!key.isValid()){
                        continue;
                    }
                    if(key.isAcceptable()){
                        accept(key);
                    }else if(key.isReadable()){
                        read(key);
                    }else if(key.isWritable()){
                    }
                }

            }catch (Exception e){
                if(key != null){
                    key.cancel();
                    Util.closeQuietly(key.channel());
                }
            }
        }

    }

    public void send(SocketChannel socket,byte[] data){
        synchronized (changeRequests){
            changeRequests.add(new ChangeRequest(socket,ChangeRequest.CHANGOPS,SelectionKey.OP_WRITE));
            synchronized (pendingSent){
                List<ByteBuffer> queue = pendingSent.get(socket);
                if(queue == null){
                    queue = new ArrayList<ByteBuffer>();
                    pendingSent.put(socket,queue);
                }
                queue.add(ByteBuffer.wrap(data));
            }
        }

        selector.wakeup();
    }


    private  void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        synchronized (pendingSent){
            List<ByteBuffer> queque = pendingSent.get(socketChannel);
            while(!queque.isEmpty()){
                ByteBuffer buf = queque.get(0);
                socketChannel.write(buf);

                if(buf.remaining() >0){
                    break;
                }
                queque.remove(0);
            }

            if(queque.isEmpty()){
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }


}
