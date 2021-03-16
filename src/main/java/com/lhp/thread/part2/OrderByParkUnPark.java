package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 顺序执行，park/unpark版,执行顺序：先2后1
 *
 * @author 53137
 */
@Slf4j(topic = "c.OrderByParkUnPark")
public class OrderByParkUnPark {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");


        });
        Thread t2 = new Thread(() -> {
            //唤醒t1
            LockSupport.unpark(t1);
            log.debug("2");
        });
        t1.start();
        t2.start();
    }
}
