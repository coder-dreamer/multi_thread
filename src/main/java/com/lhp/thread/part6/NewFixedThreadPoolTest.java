package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程数固定的线程池
 *
 * @author 53137
 */
@Slf4j(topic = "c.NewFixedThreadPoolTest")
public class NewFixedThreadPoolTest {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            AtomicInteger index = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "myThreadPool" + index.getAndIncrement());
            }
        });
        executorService.execute(() -> {
            log.debug("1");
        });
        executorService.execute(() -> {
            log.debug("2");
        });
        executorService.execute(() -> {
            log.debug("3");
        });
    }
}
