package com.yxj.controller;

import com.yxj.service.AService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-04 18:52
 */
@RestController
public class TestController {

    @Autowired
    private AService aService;


    @GetMapping("/test")
    public String test(){
        aService.test();
        return "test";
    }


    @PostMapping("/test")
    public String test2(){
        return "test";
    }


    public String test23(){
        return "test";
    }
}
