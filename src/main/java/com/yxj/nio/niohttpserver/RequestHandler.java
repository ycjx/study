package com.yxj.nio.niohttpserver;


import java.io.File;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RequestHandler  implements  Runnable{

    private static final DateFormat formater = new SimpleDateFormat(
                        "EEE,dd MM yyyy HH:mm:ss", Locale.US);

    static {
        formater.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    private ButterflySoftCache cache;
    private File currentFile;
    private Date lastModified;
    private List<RequestSegmentHeader> pendingRequestSegment = new ArrayList<RequestSegmentHeader>();
    private Map<SocketChannel,RequestHeaderDecoder> requestMap =new WeakHashMap<SocketChannel,RequestHeaderDecoder>();
    private NioHttpServer server;
    private String serverRoot;
    private String acceptEncoding;

    public RequestHandler(NioHttpServer server,String wwwroot,ButterflySoftCache cache){
        this.cache = cache;
        this.serverRoot = wwwroot;
        this.server = server;
    }

    public void  processData(SocketChannel client,byte[] data,int count){
        byte[] dataCopy = new byte[count];
        System.arraycopy(data,0,dataCopy,0,count);

        synchronized (pendingRequestSegment){
            pendingRequestSegment.add(new RequestSegmentHeader(client,dataCopy));
            pendingRequestSegment.notify();
        }
    }


    @Override
    public void run() {
        RequestSegmentHeader requestData = null;
        RequestHeaderDecoder header = null;
        ResponceHeaderBuilder builder = new ResponceHeaderBuilder();

        byte[] head = null;
        byte[] body = null;
        String file = null;
        String mime = null;
        boolean zip = false;

        while(true){
            synchronized (pendingRequestSegment){
                while(pendingRequestSegment.isEmpty()){
                    try{
                        pendingRequestSegment.wait();
                    }catch (InterruptedException e){

                    }
                    requestData = pendingRequestSegment.remove(0);
                }
            }

            header = requestMap.get(requestData.client);

            if(header == null){
                header = new RequestHeaderDecoder();
                requestMap.put(requestData.client,header);
            }
            try{
                if(header.appendSegment(requestData.data)){
                    file = serverRoot + header.getResource();
                    currentFile = new File(file);
                    mime = Util.getContentType(currentFile);
                    acceptEncoding = header.getHeader(ResponceHeaderBuilder.ACCEPT_ENCODING);
                    zip = mime.contains("text") && acceptEncoding != null
                                    && (acceptEncoding.contains("gzip")) || acceptEncoding.contains("gzip");
                    builder.clear();

                    builder.addHeader(ResponceHeaderBuilder.CONNECTION,ResponceHeaderBuilder.KEEP_ALIVE);
                    builder.addHeader(ResponceHeaderBuilder.CONTENT_TYPE,mime);

                    body = Util.file2ByteArray(currentFile,zip);
                    builder.addHeader(ResponceHeaderBuilder.CONTENT_LENGTH,body.length);
                    if(zip) {
                        builder.addHeader(ResponceHeaderBuilder.CONTENT_ENCODING,ResponceHeaderBuilder.GZIP);
                    }

                    lastModified = new Date(currentFile.lastModified());
                    builder.addHeader(ResponceHeaderBuilder.LAST_MODIFIED,formater.format(lastModified));

                    head = builder.getHeader();

                    server.send(requestData.client,head);

                    if(body != null && header.getVerb() == RequestHeaderDecoder.Verb.GET){
                        server.send(requestData.client,body);
                    }



                }
            }catch (IOException e){
                builder.addHeader(ResponceHeaderBuilder.CONTENT_LENGTH,0);
                builder.setStatus(ResponceHeaderBuilder.NOT_FOUND_404);
                head = builder.getHeader();
                server.send(requestData.client,head);
                cache.put(file,head,body);
            }catch (Exception e){
                builder.addHeader(ResponceHeaderBuilder.CONTENT_LENGTH,0);
                builder.setStatus(ResponceHeaderBuilder.SERVER_ERROR_500);
                head = builder.getHeader();
                server.send(requestData.client,head);

            }
        }
    }
}


class RequestSegmentHeader {
    SocketChannel client;
    byte[] data;

    public RequestSegmentHeader(SocketChannel client,byte[] data){
        this.client = client;
        this.data = data;
    }
}
