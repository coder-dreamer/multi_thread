package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author 53137
 */
@Slf4j(topic = "c.TestJoin")
public class TestJoin {
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = 10;
        }, "t1");
        t.start();
        //主线程等待线程t1线程运行结束，加上这句话结果为10，不加为0
        t.join();
        log.debug("结果为:{}", i);
    }
}
