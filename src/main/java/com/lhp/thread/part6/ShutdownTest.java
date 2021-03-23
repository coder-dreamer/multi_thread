package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 提交task中所有任务
 *
 * @author 53137
 */
@Slf4j(topic = "c.ShutdownTest")
public class ShutdownTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            List<Future<Object>> futures = executorService.invokeAll(Arrays.asList(() -> {
                TimeUnit.SECONDS.sleep(1);
                log.debug("begin");
                return "1";
            }, () -> {
                TimeUnit.SECONDS.sleep(2);
                log.debug("begin");
                return "2";
            }));
            futures.forEach(future->{
                try {
                    log.debug("获取结果{}",future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //执行完所有任务后停止线程池
        executorService.shutdown();
    }
}
