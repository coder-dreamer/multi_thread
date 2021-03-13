package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 53137
 */
@Slf4j(topic = "c.Sleep")
public class Sleep {
    public static void main(String[] args) {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //线程启动
        thread.start();
        //获取t1线程状态为RUNNABLE，此时t1未执行run方法中的sleep方法
        log.debug("threadName:{};status:{}", thread.getName(), thread.getState());
        //主线程休眠
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取t1线程状态为TIMED_WAITING，此时t1执行run方法中的sleep方法
        log.debug("threadName:{};status:{}", thread.getName(), thread.getState());
    }
}
