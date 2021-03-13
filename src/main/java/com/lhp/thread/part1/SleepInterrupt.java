package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 53137
 */
@Slf4j(topic = "c.SleepInterrupt")
public class SleepInterrupt {
    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                try {
                    log.debug("sleep");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("Interrupted");
                    e.printStackTrace();
                }
            }
        };
        //线程启动
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("开始打断");
        thread.interrupt();
    }
}
