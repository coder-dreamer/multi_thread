package com.lhp.thread.part3;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式v2-有锁
 * 此单例，多线程下不会出问题，但是每个线程都要进入锁，影响性能，解决办法参照SingletonV3
 *
 * @author 53137
 */
public class SingletonV2 {
    /**
     * 私有构造
     */
    private SingletonV2() {

    }

    /**
     * 私有对象
     */
    private static SingletonV2 obj = null;

    /**
     * 公有获取实例方法
     *
     * @return
     */
    public static SingletonV2 getInstance() {
        synchronized (SingletonV2.class) {
            if (obj == null) {
                obj = new SingletonV2();
            }
        }
        return obj;
    }
}

@Slf4j(topic = "c.TestSingletonV2")
class TestSingletonV2 {
    public static void main(String[] args) {
        testV1();
    }

    /**
     * 多线程下只获取了一个实例，因为加了synchronized关键字，保证同一时间只有一个线程能创建实例
     */
    public static void testV1() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                log.debug("实例地址：{}", SingletonV2.getInstance());
                log.debug("实例地址：{}", SingletonV2.getInstance());
            }).start();
        }
    }
}
