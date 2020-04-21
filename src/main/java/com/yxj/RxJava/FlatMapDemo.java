package com.yxj.RxJava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-20 23:39
 */
@Slf4j
public class FlatMapDemo {


    public static void main(String[] args) {
        //它可以把一个发射器 Observable 通过某种方法转换为多个 Observables，然
        // 后再把这些分散的 Observables装进一个单一的发射器 Observable。但有个需要注意的是，
        // flatMap 并不能保证事件的顺序，如果需要保证，需要用到我们下面要讲的 ConcatMap。

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
//                    System.out.println(integer.toString());
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) {
                        System.out.println("?");
                        log.info("flatMap : accept : " + s + "\n");
                    }
                });
    }
}
