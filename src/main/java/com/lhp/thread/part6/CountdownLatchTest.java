package com.lhp.thread.part6;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 倒计时等待
 *
 * @author 53137
 */
public class CountdownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Random random = new Random();
        String[] all = new String[10];
        try {
            for (int j = 0; j < all.length; j++) {
                int k = j;
                executorService.submit(() -> {
                    for (int i = 0; i <= 100; i++) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        all[k] = i + "%";
                        //后一个值覆盖前一个值
                        System.out.print("\r" + Arrays.toString(all));
                    }
                    //某个线程执行完毕，值减一
                    latch.countDown();
                });
            }
            //等待所有线程执行完毕
            latch.await();
            System.out.println("\n" + "游戏开始");
        } finally {
            executorService.shutdown();
        }
    }
}
