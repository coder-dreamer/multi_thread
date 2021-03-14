package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 53137
 * 同步模式之保护性暂停
 */
@Slf4j(topic = "c.GuardedSuspensionDemo")
public class GuardedSuspensionDemo {
    public static void main(String[] args) {
        GuardedSuspension demo = new GuardedSuspension();
        new Thread(() -> {

            log.debug("开始获取内容");
            Object o = demo.get(1000);
            log.debug("获取到的内容，{}", o);
        }, "t1").start();

        new Thread(() -> {
            log.debug("开始生成内容");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("内容生成完毕");
            demo.complete(new String("abc"));
        }, "t2").start();

    }
}

@Slf4j(topic = "c.GuardedSuspension")
class GuardedSuspension {
    private Object response;
    private final Object lock = new Object();

    /**
     * 获取结果：带超时时间
     *
     * @return
     */
    public Object get(long millis) {
        synchronized (lock) {
            // 1) 记录最初时间
            long begin = System.currentTimeMillis();
            // 2) 已经经历的时间
            long timePassed = 0;
            // 条件不满足则等待
            while (response == null) {
                long waitTime = millis - timePassed;
                log.debug("waitTime: {}", waitTime);
                if (waitTime <= 0) {
                    log.debug("break...");
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 3) 如果提前被唤醒，这时已经经历的时间假设为 400
                timePassed = System.currentTimeMillis() - begin;
            }

            return response;
        }
    }

    /**
     * 生成结果
     *
     * @param response
     */
    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，通知等待线程
            this.response = response;
            lock.notifyAll();
        }
    }
}