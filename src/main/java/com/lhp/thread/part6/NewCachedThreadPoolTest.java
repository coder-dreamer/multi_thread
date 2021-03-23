package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 53137
 */
@Slf4j(topic = "c.NewCachedThreadPoolTest")
public class NewCachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
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
