package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * timer用来做任务调度,延迟一秒执行
 *
 * @author 53137
 */
@Slf4j(topic = "c.TimerTest")
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        log.debug("start....");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.debug("方法执行中...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.debug("方法执行中...");
            }
        }, 1000);
    }
}
