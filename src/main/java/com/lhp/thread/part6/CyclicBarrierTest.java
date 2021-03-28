package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 循环栅栏
 *
 * @author 53137
 */
@Slf4j(topic = "c.CyclicBarrierTest")
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            //回调
            log.debug("task1 task2 end");
        });
        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                log.debug("task1 start");
                try {
                    cyclicBarrier.await(); //2-1=1
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            executorService.submit(() -> {
                log.debug("task2 start");
                try {
                    cyclicBarrier.await();//1-1=0
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
