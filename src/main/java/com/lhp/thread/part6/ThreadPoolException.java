package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 正确处理线程池异常
 *
 * @author 53137
 */
@Slf4j(topic = "c.ThreadPoolException")
public class ThreadPoolException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.debug("start....");
        //test1();
        //test2();
        test3();
    }

    private static void test3() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Boolean> submit = executorService.submit(() -> {
            log.debug("running start....");
            int i = 10 / 0;
            log.debug("running end....");
            return true;
        });
        log.debug("result:{}",submit.get());
    }

    private static void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                log.debug("running start....");
                int i = 10 / 0;
                log.debug("running end....");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            log.debug("running start....");
            int i = 10 / 0;
            log.debug("running end....");
        });
    }
}
