package com.lhp.thread.part4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicMarkableReference demo
 *
 * @author 53137
 */
public class AtomicMarkableReferenceTest {
    public static AtomicMarkableReference<String> ref = new AtomicMarkableReference<>("A", true);

    public static void main(String[] args) {
        String prev = ref.getReference();
        other();
        //此时为false,不是true,所以更新失败
        System.out.println(ref.compareAndSet(prev, "C", true, false));
        System.out.println(ref.getReference());
    }

    public static void other() {
        String prev = ref.getReference();
        System.out.println(ref.compareAndSet(prev, "B", true, false));
        System.out.println(ref.getReference());
    }
}
