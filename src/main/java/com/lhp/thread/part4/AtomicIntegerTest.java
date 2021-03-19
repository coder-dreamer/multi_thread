package com.lhp.thread.part4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子整数测试
 *
 * @author 53137
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);
        //自增并获取 类似于++i;输出1
        System.out.println(i.incrementAndGet());
        //获取并自增 类似于i++;输出1，实际为2
        System.out.println(i.getAndIncrement());
        //获取并加3，输出2，实际为5
        System.out.println(i.getAndAdd(3));
        //增加并获取 输出8
        System.out.println(i.addAndGet(3));
        //自减并获取 类似于--i;输出7
        System.out.println(i.decrementAndGet());
        //获取并自减 类似于i--;输出7，实际为6
        System.out.println(i.getAndDecrement());
        //更新并获取 6*2得12，这个方法基于cas实现
        System.out.println(i.updateAndGet(x -> x * 2));
        //获取并更新 输出12 实际为6
        System.out.println(i.getAndUpdate(x -> x / 2));
        //lambda表达式，表明要做加法操作,6+10=16
        System.out.println(i.accumulateAndGet(10, (y, z) -> y + z));
        //lambda表达式，表明要做减法操作,16-2=14,输出16，实际为14
        System.out.println(i.getAndAccumulate(2, (x, z) -> x - z));
        //验证是否输出14
        System.out.println(i.get());
    }
}
