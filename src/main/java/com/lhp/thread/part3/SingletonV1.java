package com.lhp.thread.part3;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式v1-无锁
 * 此单例，单线程下不会出问题，多线程下会出问题，解决办法参照SingletonV2
 *
 * @author 53137
 */
public class SingletonV1 {
    /**
     * 私有构造
     */
    private SingletonV1() {

    }

    /**
     * 私有对象
     */
    private static SingletonV1 obj = null;

    /**
     * 公有获取实例方法
     *
     * @return
     */
    public static SingletonV1 getInstance() {
        if (obj == null) {
            obj = new SingletonV1();
        }
        return obj;
    }
}

@Slf4j(topic = "c.TestSingletonV1")
class TestSingletonV1 {
    public static void main(String[] args) {
        //testV1();
        testV2();
    }

    /**
     * 单线程不会出问题，只获取了一个实例
     */
    public static void testV1() {
        log.debug("实例地址：{}", SingletonV1.getInstance());
        log.debug("实例地址：{}", SingletonV1.getInstance());
    }

    /**
     * 多线程下获取了一个以上实例，出现了问题，因为同一时间，可能多个线程去创建了实例
     */
    public static void testV2() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                log.debug("实例地址：{}", SingletonV1.getInstance());
                log.debug("实例地址：{}", SingletonV1.getInstance());
            }).start();
        }
    }
}
