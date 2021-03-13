package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全之共享变量
 *
 * @author 53137
 */
@Slf4j(topic = "c.ThreadUnSafeOfCommonVar")
public class ThreadUnSafeOfCommonVar {

    public static void main(String[] args) throws InterruptedException {
        UnsafeTest unsafeTest = new UnsafeTest();
        //多个线程操作同一个list,会发生线程安全问题
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                unsafeTest.method1();
            }, "t" + i).start();
        }
    }
}

class UnsafeTest {
    List<Integer> list = new ArrayList<>();

    public void method1() {
        for (int i = 0; i < 200; i++) {
            method2();
            method3();
        }
    }

    public void method2() {
        list.add(1);
    }

    public void method3() {
        list.remove(0);
    }

}
