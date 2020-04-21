package com.yxj.RxJava;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;


/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-20 23:35
 */
@Slf4j
public class ConcatDemo {


    public static void main(String[] args) {

        //组合嘛，输出123456
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        log.info(integer.toString());
                    }
                });
    }
}
