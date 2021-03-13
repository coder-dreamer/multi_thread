package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 调试多线程：断点Suspend状态设置为Thread
 * 以debug方式运行，观察栈帧信息
 *
 * @author 53137
 */
@Slf4j(topic = "c.DebugThreads")
public class DebugThreads {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                log.debug("{}", method1(1));
            }
        }.start();
        log.debug("{}", method1(1));
    }

    private static int method1(int x) {
        int y = x + 1;
        return method2(y);
    }

    private static int method2(int y) {
        int z = y + 1;
        return z;
    }

}
