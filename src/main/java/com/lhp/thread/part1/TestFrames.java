package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试栈帧
 * 以debug方式运行，观察栈帧信息
 *
 * @author 53137
 */
@Slf4j(topic = "c.TestFrames")
public class TestFrames {
    public static void main(String[] args) {
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
