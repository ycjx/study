package com.yxj.RxJava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-20 22:07
 */
@Slf4j
public class Demo {


    public static void main(String[] args) {

        Observable<Integer> objectObservable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                log.info("Observable emit 1" + "\n");
                emitter.onNext(1);
                log.info("Observable emit 2" + "\n");
                emitter.onNext(2);
                log.info("Observable emit 3" + "\n");
                emitter.onNext(3);
                //无法接收事件了，但还是可以发送事件的
                emitter.onComplete();
                log.info("Observable emit 4" + "\n");
                emitter.onNext(4);
            }
        }).map(new Function<Integer, Integer>() {

            //对发送的事件应用一个函数
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer * 10;
            }
        });

        Observable<String> lift = objectObservable.lift(new ObservableOperator<String, Integer>() {

            @Override
            public Observer<? super Integer> apply(Observer<? super String> observer) throws Exception {
                return new Observer<Integer>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer o) {
                        System.out.println("转换成String");
                        observer.onNext(o.toString()+"String");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        observer.onSubscribe(d);
                    }

                };
            }
        });
        lift.subscribe(new Observer<String>() {

            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("这是观察者一");

            }

            @Override
            public void onNext(String o) {
                System.out.println("观察这一  " + o);
                if (o.equals(2)) {
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });

//        objectObservable.subscribe(new Observer<Integer>() {
//
//            private Disposable mDisposable;
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("这是观察者二");
//
//            }
//
//            @Override
//            public void onNext(Integer o) {
//                System.out.println("观察者二  " + o);
////                if (o.equals(2)) {
////                    mDisposable.dispose();
////                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("complete");
//            }
//        });
    }
}
