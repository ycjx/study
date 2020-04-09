package com.yxj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-07-06 15:42
 */
@Configuration
public class KogMawConfiguration {


    @Bean
    public KogMawListener kogMawListener(){
        return new KogMawListener();
    }
}
