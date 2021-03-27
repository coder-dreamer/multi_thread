package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author 53137
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) throws InterruptedException {
        //test();
        //test1();
        //test2();
        //test3();
        test4();

    }

    /**
     * 先写后读-可以
     */
    private static void test4() {
        DataContainer data = new DataContainer();
        data.writeRead();
    }

    /**
     * 先获取读锁，再获取写锁，会导致等待
     */
    private static void test3() {
        DataContainer data = new DataContainer();
        data.readWrite();
    }

    /**
     * 写写互斥
     *
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {
        DataContainer data = new DataContainer();
        new Thread(() -> {
            data.write();
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            data.write();
        }, "t2").start();
    }

    /**
     * 读写互斥
     *
     * @throws InterruptedException
     */
    private static void test1() throws InterruptedException {
        DataContainer data = new DataContainer();
        new Thread(() -> {
            data.read();
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            data.write();
        }, "t2").start();
    }

    /**
     * 读-读并发进行
     */
    private static void test() {
        DataContainer data = new DataContainer();
        new Thread(() -> {
            data.read();
        }, "t1").start();

        new Thread(() -> {
            data.read();
        }, "t2").start();
    }
}

/**
 * 数据容器-存储数据并提供数据的读写方法
 */
@Slf4j(topic = "c.DataContainer")
class DataContainer {
    /**
     * 数据
     */
    private Object obj;
    /**
     * 创建读写锁
     */
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    /**
     * 写锁
     */
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    /**
     * 读取数据
     *
     * @return
     */
    public Object read() {
        readLock.lock();
        log.debug("获取读锁");
        try {
            log.debug("读取数据");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("释放读锁");
            readLock.unlock();
        }
        return obj;
    }

    public void write() {
        writeLock.lock();
        log.debug("获取写锁");
        try {
            log.debug("写入数据");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("释放写锁");
            writeLock.unlock();
        }
    }

    public void readWrite() {
        readLock.lock();
        log.debug("获取读锁");
        try {
            writeLock.lock();
            log.debug("获取写锁");
            try {
                log.debug("写入数据");
                TimeUnit.SECONDS.sleep(2);
            } finally {
                log.debug("释放写锁");
                writeLock.unlock();
            }
            log.debug("读取数据");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("释放读锁");
            readLock.unlock();
        }
    }

    public void writeRead() {
        writeLock.lock();
        log.debug("获取写锁");
        try {
            readLock.lock();
            log.debug("获取读锁");
            try {
                log.debug("读取数据");
                TimeUnit.SECONDS.sleep(2);
            } finally {
                log.debug("释放读锁");
                readLock.unlock();
            }
            log.debug("写入数据");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("释放写锁");
            writeLock.unlock();
        }
    }
}
