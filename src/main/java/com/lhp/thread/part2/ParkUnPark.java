package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 53137
 */
@Slf4j(topic = "c.ParkUnPark")
public class ParkUnPark {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("park");
            LockSupport.park();
            log.debug("继续执行");
        }, "t1");
        t1.start();
        Thread.sleep(2000);
        log.debug("unpark");
        LockSupport.unpark(t1);
    }
}
