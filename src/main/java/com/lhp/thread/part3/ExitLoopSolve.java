package com.lhp.thread.part3;

import lombok.extern.slf4j.Slf4j;

/**
 * 退不出的循环-解决方案
 *
 * @author 53137
 */
@Slf4j(topic = "c.ExitLoopSolve")
public class ExitLoopSolve {
    volatile static boolean flag = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (flag) {

            }
        }, "t1");

        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("t1 Stop");
        flag = false;
    }
}
