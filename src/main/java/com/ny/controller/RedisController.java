package com.ny.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@RestController
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    private RedisTemplate<String, String> redis;

    private ExecutorService pool = Executors.newFixedThreadPool(100);

    public RedisController(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    public static void main(String[] args) {
        final SInt si = new SInt();
        LongStream.range(0, 100000000l).forEach(i -> {
            si.i = si.i + 1;
        });
        System.out.println(si.i);
    }

    static class SInt {
        int i = 0;
        void ins() {
            ++i;
        }
    }

    @GetMapping("/redis/add")
    public void add(int size) {
        long st = System.currentTimeMillis();
        IntStream.range(0, 1000).forEach(i -> {
            submitTask("key", "value" + i);
        });
        logger.info("finish cost {}ms", System.currentTimeMillis() - st);
    }

    private void submitTask(final String key, final String value) {
        pool.submit(() -> {
            redis.boundValueOps(key).increment(1);
            logger.info("Thread {} add key {} value {}", Thread.currentThread().getName(),
                    key, value);

        });
    }
}
