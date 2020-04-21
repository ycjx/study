package com.yxj.RxJava;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-21 10:05
 */
@Slf4j
public class SingleDemo {

    public static void main(String[] args) {
        Single<Object> objectSingle = Single.create(new SingleOnSubscribe<Object>() {

            @Override
            public void subscribe(SingleEmitter<Object> emitter) throws Exception {
                System.out.println("进行中");
                emitter.onSuccess("hehe");
//                throw new Exception();

            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        objectSingle.subscribe(new SingleObserver<Object>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("开始喽");
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println("成功咯");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("失败咯");
            }
        });


    }
}
