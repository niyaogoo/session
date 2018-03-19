package com.ny.rx;

import com.netflix.hystrix.*;
import org.apache.commons.lang.math.IntRange;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class HystrixTest extends HystrixCommand<String> {

    private final String name;

    private HystrixTest(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixTest"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HystrixTestPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(2))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //TimeUnit.SECONDS.sleep(1);
        return "Hello hystrix";
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }

    public static void main(String[] args) throws Exception {

        //command.execute();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        IntStream.range(0, 100).forEach(i -> {
            pool.submit(() -> {
                HystrixTest command = new HystrixTest("test");
                System.out.println(i);
                Future<String> rtn = command.queue();
                try {
                    System.out.println(i + "complete" + rtn.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        });
        pool.shutdown();
    }

}
