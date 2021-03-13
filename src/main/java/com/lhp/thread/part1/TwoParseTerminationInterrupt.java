package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用Interrupt方法实现两阶段终止
 *
 * @author 53137
 */
public class TwoParseTerminationInterrupt {
    public static void main(String[] args) {
        TwoParseTermination twoParseTermination = new TwoParseTermination();
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
@Slf4j(topic = "c.TwoParseTermination")
class TwoParseTermination {
    /**
     * 监控线程
     */
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                //被打断
                if (interrupted) {
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
                    //重设打断标记
                    Thread.currentThread().interrupt();
                }
            }
        }, "monitor");
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}