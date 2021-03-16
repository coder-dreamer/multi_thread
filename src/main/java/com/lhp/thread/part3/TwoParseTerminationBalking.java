package com.lhp.thread.part3;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止之（Balking【阻止】模式）
 *
 * @author 53137
 */
public class TwoParseTerminationBalking {
    public static void main(String[] args) {
        TwoParseTerminationB twoParseTermination = new TwoParseTerminationB();
        //开始监控----同时启动两个start方法，使用Balking模式后，只执行一次监控操作
        twoParseTermination.start();
        twoParseTermination.start();
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //终止监控
        twoParseTermination.stop();

    }
}

/**
 * 两阶段终止
 */
@Slf4j(topic = "c.TwoParseTerminationB")
class TwoParseTerminationB {
    /**
     * 监控线程
     */
    private Thread monitor;
    /**
     * 设置标记，用于判断是否被终止了
     */
    private volatile boolean stop = false;
    /**
     * 设置标记，用于判断是否已经启动过了
     */
    private boolean starting = false;

    public void start() {
        // 上锁，避免多线程运行时出现线程安全问题
        synchronized (this) {
            if (starting) {
                // 已被启动，直接返回
                return;
            }
            // 启动监视器，改变标记
            starting = true;
        }
        monitor = new Thread(() -> {
            while (true) {
                //被打断
                if (stop) {
                    log.debug("进行释放资源操作");
                    break;
                }
                //执行监控
                try {
                    //若此时被打断，会进入异常，打断标记为false，重设打断标记即可
                    Thread.sleep(1000);
                    //若此时被打断，打断标记为true，退出循环
                    log.debug("执行监控");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "monitor");
        monitor.start();
    }

    public void stop() {
        this.stop = true;
        monitor.interrupt();
    }
}