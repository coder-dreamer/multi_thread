package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试线程优先级
 *
 * @author 53137
 */
@Slf4j(topic = "c.TestPriority")
public class TestPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int i = 0;
            for (; ; ) {
                log.debug("---->t1:{}", i++);
            }
        });
        t1.setName("t1");
        Thread t2 = new Thread(() -> {
            int i = 0;
            for (; ; ) {
                //Thread.yield();
                log.debug("         ---->t2:{}", i++);
            }
        });
        t2.setName("t2");
        //1.正常执行
        //结果相近
        //16:07:16.315 [t1] c.TestPriority - ---->t1:396792
        //16:07:16.315 [t2] c.TestPriority -          ---->t2:374691

        //2.t2线程调用Thread.yield();
        //结果：t1的值远大于t2的值
        //16:09:45.716 [t1] c.TestPriority - ---->t1:598531
        //16:09:45.716 [t2] c.TestPriority -          ---->t2:383849

        //3.t1和t2设置不同的优先级
        //结果：t2的值远大于t1的值
        //16:12:22.545 [t2] c.TestPriority -          ---->t2:477558
        //16:12:22.545 [t1] c.TestPriority - ---->t1:397949
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
