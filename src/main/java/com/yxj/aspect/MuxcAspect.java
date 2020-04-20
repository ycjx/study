package com.yxj.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-13 15:08
 */
@Component
@Aspect
public class MuxcAspect {


    @Pointcut("execution(* com.yxj.service.*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("aopBefore");
    }

}
