package com.yxj.nio.niohttpserver;


import com.samskivert.mustache.Mustache;

import java.io.*;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.GZIPOutputStream;

class FileItem implements Comparable<FileItem>{

    public final String href;

    public final String name;

    public final String size;

    public final String mtime;

    public FileItem(String href,String name,String size,String mtime){
        this.href = href;
        this.name = name;
        this.size = size;
        this.mtime = mtime;
    }


    @Override
    public int compareTo(FileItem o) {
        return name.compareTo(name);
    }
}

public class Util {

    private static String defaultType = "application/octet-stream";
    private static  String mapFile = "mime.types";
    private static String indexTmpl = "index.tp1";
    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-HH-dd HH:mm:ss");


    /**
     * 获取文件后缀名
     * @param file 123.txt
     * @return  txt
     */
    public static String getExtension(File file){
        String name = file.getName();
        int index = name.lastIndexOf(".");
        if(index != -1){
            return name.substring(index+1).toLowerCase();
        }else{
            return null;
        }
    }


    /**
     * 返回一个object对象
     * @param folder 文件夹
     * @return 文件夹里的文件和子文件夹
     */
    public static Object listDir(final File folder){
        File[] files = folder.listFiles();
        final List<FileItem> fileItemList = new ArrayList<FileItem>();
        for(File file : files){
            String href = file.isDirectory()?file.getName()+"/" : file.getName();
            String mtime = df.format(LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()));
            fileItemList.add(new FileItem(href,file.getName(),file.length()+"",mtime));

        }

        Collections.sort(fileItemList);
        return new Object(){
            Object files = fileItemList;
            Object dir = folder.getName();
        };
    }

    /**
     *
     * @param dir
     * @param zip 是否保存为zip
     * @return 返回字节数组
     */
    public static byte[] directoryList(File dir,boolean zip){
        StringBuilder sb = new StringBuilder(300);
        InputStream in = Util.class.getClassLoader().getResourceAsStream(indexTmpl);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        try {
            while((line = br.readLine())!= null){
                sb.append(line);
                sb.append("\n");
            }
        }catch (IOException e){

        }
        String html = Mustache.compiler().compile(sb.toString()).execute(listDir(dir));

        if(zip){
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream(8912);
                GZIPOutputStream gzip = new GZIPOutputStream(baos);
                gzip.write(html.getBytes());
                closeQuietly(gzip);
                return baos.toByteArray();
            }catch (IOException e){

            }
        }else{
            return html.getBytes();
        }
        return  new byte[]{};

    }

    public static String getContentType(File file){
        if(file.isDirectory())
            return "text/html";
        InputStream in = Util.class.getClassLoader().getResourceAsStream(mapFile);
        String exten = getExtension(file);
        Map<String,String> map = new HashMap<String,String>();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = bf.readLine()) != null){
                String[] tmp = line.split("\\s+");
                map.put(tmp[0],tmp[1]);
            }
        }catch (IOException e){

        }
        if(map.get(exten) == null){
            return defaultType;
        }else{
            return map.get(exten);
        }
    }

    /**
     * 关闭 流
     * @param is
     */
    public static void closeQuietly(Closeable is){
        if(is != null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * @param file
     *            the absolute file path
     * @param zip
     *            gzip or not
     * @return byte array of the file
     * @throws IOException
     */
    public static byte[] file2ByteArray(File file, boolean zip)
            throws IOException {
        if (file.isFile()) {
            InputStream is = null;
            GZIPOutputStream gzip = null;
            byte[] buffer = new byte[8912];
            ByteArrayOutputStream baos = new ByteArrayOutputStream(8912);
            try {
                if (zip) {
                    gzip = new GZIPOutputStream(baos);
                }
                is = new BufferedInputStream(new FileInputStream(file));
                int read = 0;
                while ((read = is.read(buffer)) != -1) {
                    if (zip) {
                        gzip.write(buffer, 0, read);
                    } else {
                        baos.write(buffer, 0, read);
                    }
                }
            } catch (IOException e) {
                throw e;
            } finally {
                closeQuietly(is);
                closeQuietly(gzip);
            }
            return baos.toByteArray();
        } else if (file.isDirectory()) {
            return directoryList(file, zip);
        } else {
            return new byte[] {};
        }
    }



    /**

     * 从后往前找
     * to start;
     * @param data
     *            to search from
     * @param tofind
     *            target
     * @param start
     *            start index
     * @return index of the first find if find, data.length if not find
     */
    public static int subArrayFromEnd(byte[] data, byte[] tofind, int start) {

        int index = data.length;

        outer: for (int i = data.length - tofind.length; i > 0; --i) {
            for (int j = 0; j < tofind.length;) {
                if (data[i] == tofind[j]) {
                    ++i;++j;
                    if (j == tofind.length) {
                        index = i - tofind.length;
                        break outer;
                    }
                } else {
                    i = i - j; // step back
                    break;
                }
            }
        }
        return index;
    }



    /**

     *

     * @param data  数据源
     * @param tofind 要搜索到的数据
     * @param start 开始位置
     * @return 第一个找到的index 没有找到则返回data.length

     */

    public static int subArray(byte[] data, byte[] tofind, int start) {

        int index = data.length;
        outer: for (int i = start; i < data.length; ++i) {
            for (int j = 0; j < tofind.length;) {
                if (data[i] == tofind[j]) {
                    ++i;
                    ++j;
                    if (j == tofind.length) {
                        index = i - tofind.length;
                        break outer;
                    }
                } else {
                    i = i - j; // step back
                    break;
                }
            }
        }

        return index;

    }



}
