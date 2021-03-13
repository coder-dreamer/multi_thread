package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * 共享带来的线程安全问题
 *
 * @author 53137
 */
@Slf4j(topic = "c.ThreadSafe")
public class ThreadSafe {
    static int a = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                a++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                a--;
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //预想的是0，实际不是0，存在线程安全问题
        log.debug("a的结果：{}", a);
    }
}
