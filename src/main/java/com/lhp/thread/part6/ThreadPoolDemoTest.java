package com.lhp.thread.part6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池demo
 *
 * @author 53137
 */
public class ThreadPoolDemoTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            //处理10个请求
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println("线程执行任务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            executorService.shutdown();
        }
    }
}
