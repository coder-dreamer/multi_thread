package com.lhp.thread.part4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用ABA问题
 *
 * @author 53137
 */
public class ABATest {
    public static AtomicReference<String> ref = new AtomicReference<>("A");

    public static void main(String[] args) throws InterruptedException {
        String prev = ref.get();
        other();
        //true
        TimeUnit.SECONDS.sleep(1);
        //过程A->B->A->C；简称ABA问题
        System.out.println(ref.compareAndSet(prev, "C"));
        //C
        System.out.println(ref.get());
    }

    public static void other() throws InterruptedException {
        new Thread(() -> {
            String prev = ref.get();
            //true
            System.out.println(ref.compareAndSet(prev, "B"));
            //B
            System.out.println(ref.get());
        }).start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            String prev = ref.get();
            //true
            System.out.println(ref.compareAndSet(prev, "A"));
            //A
            System.out.println(ref.get());
        }).start();

    }
}
