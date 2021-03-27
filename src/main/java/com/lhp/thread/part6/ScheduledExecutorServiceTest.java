package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 改进timer；延时执行任务&定时执行任务
 *
 * @author 53137
 */
@Slf4j(topic = "c.ScheduledExecutorServiceTest")
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        log.debug("start....");
        //test1(executorService);
        test2(executorService);

        test3(executorService);
    }

    /**
     * 定时执行任务，以固定的间隔
     *
     * @param executorService
     */
    private static void test3(ScheduledExecutorService executorService) {
        executorService.scheduleWithFixedDelay(() -> {
            log.debug("running....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 定时执行任务，以固定的频率
     *
     * @param executorService
     */
    private static void test2(ScheduledExecutorService executorService) {
        executorService.scheduleAtFixedRate(() -> {
            log.debug("running....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 延时执行任务
     *
     * @param executorService
     */
    private static void test1(ScheduledExecutorService executorService) {

        executorService.schedule(() -> {
            log.debug("running....");
        }, 1, TimeUnit.SECONDS);
        executorService.schedule(() -> {
            log.debug("running....");
        }, 1, TimeUnit.SECONDS);
    }
}
