package com.ny.foo;

import feign.Feign;
import feign.Request;
import feign.RequestLine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class FooService {

    public static void main(String[] args) {
        Foo foo = Feign.builder()
                .options(new Request.Options(1000, 10000))
                .target(Foo.class, "http://localhost:8001");
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        final AtomicInteger total = new AtomicInteger();
        IntStream.range(0, 1000).forEach(e -> {
            pool.execute(() -> {
//                try {
                    System.out.println(total.incrementAndGet() + " : " + foo.addFoo());
  //              } catch(Throwable ex) {
      //              System.out.println("fail : " + total.incrementAndGet() + " exp: " + ex.getMessage());
    //            }
            });
        });
        System.out.println("all task submitted");
        pool.shutdown();

    }

    interface Foo {
        @RequestLine("POST /foo/add")
        String addFoo();
    }
}
