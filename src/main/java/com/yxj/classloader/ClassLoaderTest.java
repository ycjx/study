package com.yxj.classloader;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-05-28 11:06
 */
public class ClassLoaderTest {

    private static final String SERVICES_DIRECTORY = "org/springframework/";



    public static void main(String[] args) throws Exception {
        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        System.out.println(classLoaderTest.getClass().getInterfaces());
        Class<? extends ClassLoaderTest> aClass = classLoaderTest.getClass();
        List<Class> extensions = new ArrayList<>();
        System.out.println(aClass.getClassLoader().getResource(""));

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL resource = contextClassLoader.getResource("com/yxj/classloader/ClassLoaderTest.class");
        System.out.println(resource.getFile());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        classLoaderTest.loadFile(aClass,SERVICES_DIRECTORY,Thread.currentThread().getContextClassLoader(),extensions);

    }


    private  void loadFile(Class<?> service, String dir, ClassLoader classLoader, List<Class> extensions)
            throws IOException {
        String fileName = dir;
        Enumeration<URL> urls;
        if (classLoader != null) {
            urls = classLoader.getResources(fileName);
        } else {
            urls = ClassLoader.getSystemResources(fileName);
        }

        if (urls != null) {
            while (urls.hasMoreElements()) {
                java.net.URL url = urls.nextElement();
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        final int ci = line.indexOf('#');
                        if (ci >= 0) {
                            line = line.substring(0, ci);
                        }
                        line = line.trim();
                        if (line.length() > 0) {
                            extensions.add(Class.forName(line, true, classLoader));
                        }
                    }
                } catch (ClassNotFoundException e) {
                } catch (Throwable e) {
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException ioe) {
                    }
                }
            }
        }
    }

}
