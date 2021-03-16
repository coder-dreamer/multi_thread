package com.lhp.thread.part3;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用Volatile方法实现两阶段终止
 *
 * @author 53137
 */
public class TwoParseTerminationVolatile {
    public static void main(String[] args) {
        TwoParseTerminationV twoParseTermination = new TwoParseTerminationV();
        //开始监控
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
@Slf4j(topic = "c.TwoParseTerminationV")
class TwoParseTerminationV {
    /**
     * 监控线程
     */
    private Thread monitor;
    /**
     * 设置标记，用于判断是否被终止了
     */
    private volatile boolean stop = false;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
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