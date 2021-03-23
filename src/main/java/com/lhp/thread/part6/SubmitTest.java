package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 线程提交
 *
 * @author 53137
 */
@Slf4j(topic = "c.SubmitTest")
public class SubmitTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "testSubmit";
            }
        });
        try {
            log.debug("获取结果{}", submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
