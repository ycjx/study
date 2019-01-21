package com.yxj;

import java.util.concurrent.Callable;

public class CallA implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "this is Callable";
    }
}
