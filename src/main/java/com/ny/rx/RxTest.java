package com.ny.rx;


import com.google.common.collect.Lists;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxTest {

    public static void main(String[] args) throws Exception {
        /*PublishSubject<String> subject = PublishSubject.create();
        Observable.create((Observable.OnSubscribe<String>)subscriber -> {
            subscriber.onNext("as Bridge");

        })
        .subscribe(subject);
        subject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("subject:" + s);
            }
        });
        subject.onNext("1");
        subject.onNext("2");*/

        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hi，Weavey！");  //发送数据"Hi，Weavey！"
            }
        });
        sender.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });

        sender.map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return 1;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });

    }

    static class LogUtil {
        static void log(String msg) {
            System.out.println(msg);
        }
    }

}
