package com.lhp.thread.part3;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式v4-双重检查加volatile（double-check-locking）
 *
 * @author 53137
 */
public class SingletonV4 {
    /**
     * 私有构造
     */
    private SingletonV4() {

    }

    /**
     * 私有对象
     */
    private static volatile SingletonV4 obj = null;

    /**
     * 公有获取实例方法
     *
     * @return
     */
    public static SingletonV4 getInstance() {
        if (obj == null) {
            synchronized (SingletonV4.class) {
                if (obj == null) {
                    obj = new SingletonV4();
                }
            }
        }
        return obj;
    }
}

@Slf4j(topic = "c.TestSingletonV4")
class TestSingletonV4 {
    public static void main(String[] args) {
        testV1();
    }

    public static void testV1() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                log.debug("实例地址：{}", SingletonV4.getInstance());
                log.debug("实例地址：{}", SingletonV4.getInstance());
            }).start();
        }
    }
}
