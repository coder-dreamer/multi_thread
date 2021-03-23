package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 哪个任务先成功执行完毕
 *
 * @author 53137
 */
@Slf4j(topic = "c.InvokeAnyTest")
public class InvokeAnyTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            String result = executorService.invokeAny(Arrays.asList(() -> {
                log.debug("begin");
                TimeUnit.SECONDS.sleep(1);
                return "1";
            }, () -> {
                log.debug("begin");
                TimeUnit.SECONDS.sleep(2);
                return "2";
            }, () -> {
                log.debug("begin");
                TimeUnit.SECONDS.sleep(3);
                return "3";
            }));
            //只返回1
            log.debug("获取结果{}", result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
