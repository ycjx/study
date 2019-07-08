package com.yxj;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Demo1 {


    private static final int MAX_BUFFER_SIZE = 1000000;



    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();



        //得到输入流
        InputStream inputStream =  url.openStream();
//        System.out.println(url.ge);

        URL url2 = new URL(conn.getHeaderField("Location"));

        HttpURLConnection conn2= (HttpURLConnection) url2.openConnection();



        //得到输入流
        InputStream inputStream2 =  conn2.getInputStream();


        //获取自己数组
        byte[] getData = readInputStream(inputStream2);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }


        System.out.println("info:" + url + " download success");

    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        try {
            for(int i=701001;i<702000;i++){
                System.out.println(i);
                try {
                    downLoadFromUrl("http://3333box.com/video/play/"+i,
                            i+".mp4", "/Users/ycjx/xxx");
                }catch (Exception ex){
                    System.out.println(i+"下载失败");
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
        }

//        downLoadFromUrl("http://2222box.com/video/play/700000",
//                "700000.mp4", "/Users/ycjx/xxx");



    }


}
