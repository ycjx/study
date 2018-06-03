package com.yxj.nio.niohttpserver;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RequestHeaderDecoder {

    public static enum Verb{
        CONNECT,DELETE,GET,HEAD,OPTIONS,PATCH,POST,PUT,TRACE
    }

    public static enum Version{
        HTTP10,HTTP11
    }

    private static CharsetDecoder decoder = Charset.forName("ISO-8859-1")
            .newDecoder();

    private static final byte[] END = new byte[]{ 13,10,13,10};
    private static final byte[] GET = new byte[]{71,69,84,32};
    private static final byte[] HEAD = new byte[]{72,69,65,68};

    private boolean begin = false;

    private CharBuffer charBuffer = ByteBuffer.allocate(2048).asCharBuffer();

    private Map<String,String> headerMap = new TreeMap<String,String>();

    private String resource;
    private Verb verb;

    public boolean appendSegment(byte[] segmrnt){
        int beginIndex = 0;

        if(begin == false){
            if((beginIndex = Util.subArray(segmrnt,GET,0)) != segmrnt.length){
                begin = true;
                headerMap.clear();
                verb = verb.GET;
            }else if (( beginIndex = Util.subArray(segmrnt,HEAD,0)) != segmrnt.length){
                begin = true;
                headerMap.clear();
                verb = verb.HEAD;
            }else{
                return false;
            }
        }

        int endIndex = Util.subArrayFromEnd(segmrnt,END,0);
        ByteBuffer b = ByteBuffer.wrap(segmrnt,beginIndex,endIndex);
        decoder.decode(b,charBuffer,endIndex != segmrnt.length);
        if(endIndex != segmrnt.length){
            return true;
        }
        return false;
    }


    private void extractValueAndReset(){
        charBuffer.flip();
        String head = charBuffer.toString();
        String[] lines = head.split("\r\n");
        String[] split = lines[0].split(" ");
        resource = split[1];

        for(int i=0;i<lines.length;i++){
            String[] temp = lines[i].split(":");
            headerMap.put(temp[0].trim(),temp[1].trim());
        }

        charBuffer.clear();
        decoder.reset();
        begin = false;
    }

    public String getHeader(String key){
        return headerMap.get(key);
    }

    public Set<String> getHeaders(){
        return headerMap.keySet();
    }

    public String getResource() {
        return resource;
    }

    public Verb getVerb(){
        return verb;
    }

    public  Version getVersion(){
        throw  new RuntimeException(" not implement yet");
    }



}
