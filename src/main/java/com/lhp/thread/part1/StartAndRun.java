package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试start和run方法
 *
 * @author 53137
 */
@Slf4j(topic = "c.StartAndRun")
public class StartAndRun {
    public static void main(String[] args) {
        //run();
        start();
    }

    private static void run() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                log.debug("run......");
            }
        };
        thread.setName("t1");
        thread.run();
        log.debug("run......");
    }

    private static void start() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                log.debug("run......");
            }
        };
        thread.setName("t2");
        thread.start();
        //测试多次调用start方法
        //thread.start();
        log.debug("run......");
    }

}
