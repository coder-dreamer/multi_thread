package com.lhp.thread.part3;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式v3-双重检查（double-check-locking）
 * 此单例，只有第一次或进入锁，之后不会去获取锁，但是可能会指令重排，解决办法参照SingletonV4
 *
 * @author 53137
 */
public class SingletonV3 {
    /**
     * 私有构造
     */
    private SingletonV3() {

    }

    /**
     * 私有对象
     */
    private static SingletonV3 obj = null;

    /**
     * 公有获取实例方法
     *
     * @return
     */
    public static SingletonV3 getInstance() {
        if (obj == null) {
            synchronized (SingletonV3.class) {
                if (obj == null) {
                    obj = new SingletonV3();
                }
            }
        }
        return obj;
    }
}

@Slf4j(topic = "c.TestSingletonV3")
class TestSingletonV3 {
    public static void main(String[] args) {
        testV1();
    }

    public static void testV1() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                log.debug("实例地址：{}", SingletonV3.getInstance());
                log.debug("实例地址：{}", SingletonV3.getInstance());
            }).start();
        }
    }
}
