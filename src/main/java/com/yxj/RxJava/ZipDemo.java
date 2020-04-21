package com.yxj.RxJava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-20 22:49
 */
@Slf4j
public class ZipDemo {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        List<String> strings = new ArrayList<>();
        strings.add("A:");
        strings.add("B:");


        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                sb.append("Observable emit 1" + "\n");
                log.info("Observable emit 1" + "\n");
                emitter.onNext(1);
                sb.append("Observable emit 2" + "\n");
                log.info("Observable emit 2" + "\n");
                emitter.onNext(2);
                sb.append("Observable emit 3" + "\n");
                log.info("Observable emit 3" + "\n");
                emitter.onNext(3);

                //无法接收事件了，但还是可以发送事件的
                emitter.onComplete();
                sb.append("Observable emit 4" + "\n");
                log.info("Observable emit 4" + "\n");
                emitter.onNext(4);
            }
        }).zipWith(strings, new BiFunction<Integer, String, String>() {

            //将strings 与发布者的消息合并，已消息少的那个为准
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return s + integer;
            }
        });

        stringObservable.subscribe(new Observer<String>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("这是观察者一");

            }

            @Override
            public void onNext(String o) {
                System.out.println("观察这一  " + o);
//                if (o.equals(2)) {
//                    mDisposable.dispose();
//                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });

        //也可以将两个Observable合并
        Observable<String> zip = Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        });

        zip.subscribe(new Observer<String>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("这是观察者一");

            }

            @Override
            public void onNext(String o) {
                System.out.println("观察者一  " + o.toString());
//                if (o.equals(2)) {
//                    mDisposable.dispose();
//                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });


    }


    public static Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext("A");
                    log.info("String emit : A \n");
                    e.onNext("B");
                    log.info("String emit : B \n");
                    e.onNext("C");
                    log.info("String emit : C \n");
                }
            }
        });
    }

    public static Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(1);
                    log.info("Integer emit : 1 \n");
                    e.onNext(2);
                    log.info("Integer emit : 2 \n");
                    e.onNext(3);
                    log.info("Integer emit : 3 \n");
                    e.onNext(4);
                    log.info("Integer emit : 4 \n");
                    e.onNext(5);
                    log.info("Integer emit : 5 \n");
                }
            }
        });
    }
}
