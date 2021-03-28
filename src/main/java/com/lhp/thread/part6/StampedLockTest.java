package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 戳锁
 *
 * @author 53137
 */
public class StampedLockTest {
    public static void main(String[] args) throws InterruptedException {
        StampedLockDataContainer dataContainer = new StampedLockDataContainer(1);

        new Thread(() -> {
            try {
                System.out.println(dataContainer.read(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();


        TimeUnit.MILLISECONDS.sleep(500);

        new Thread(() -> {
            //先读再写会导致锁升级
            dataContainer.write(10);
        }, "t2").start();
    }
}

@Slf4j(topic = "c.StampedLockDataContainer")
class StampedLockDataContainer {
    /**
     * 共享数据
     */
    private int data;
    private StampedLock stampedLock = new StampedLock();

    public StampedLockDataContainer(int data) {
        this.data = data;
    }

    public int read(int readTime) throws InterruptedException {
        //尝试乐观读
        long stamp = stampedLock.tryOptimisticRead();
        log.info("optimistic read locking ...{}", stamp);
        Thread.sleep(readTime * 1000);
        //校验戳；一致获取数据；不一致锁升级
        if (stampedLock.validate(stamp)) {
            log.info("read finish... {}", stamp);
            return data;
        }
        // 锁升级 - 读锁
        log.info("update to read lock ...");
        try {
            stamp = stampedLock.readLock();
            log.info("read lock {}", stamp);
            Thread.sleep(readTime * 1000);
            log.info("read finish ... {}", stamp);
            return data;
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    public void write(int newData) {
        //加写锁
        long stamp = stampedLock.writeLock();
        try {
            log.info("write lock {}", stamp);
            this.data = newData;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("write finish ... {}", stamp);
            log.info("write newData ... {}", this.data);
        } finally {
            //解锁
            stampedLock.unlockWrite(stamp);
        }
    }
}
