package com.yxj;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-06-08 23:13
 */
public class Spring {

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("Test.java"); // <1>
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory(); // <2>
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory); // <3>
        reader.loadBeanDefinitions(resource);
    }
}
