package com.yxj.RxJava;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-21 00:00
 */
@Slf4j
public class BufferDemo {

    public static void main(String[] args) {

        //先截取三个1，2，3，再跳两步从1跳到3
        //再截取三个3，4，5，再跳两部3到5
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        log.info("buffer size : " + integers.size() + "\n");
                        log.info("buffer value : ");
                        for (Integer i : integers) {
                            log.info(i + "");
                        }
                    }
                });
    }
}
