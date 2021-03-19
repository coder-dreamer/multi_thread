package com.lhp.thread.part4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference 使用版本号解决ABA问题
 *
 * @author 53137
 */
public class AtomicStampedReferenceTest {
    public static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

    public static void main(String[] args) throws InterruptedException {
        String prev = ref.getReference();
        int stamp = ref.getStamp();
        //0
        System.out.println(stamp);
        other();
        TimeUnit.SECONDS.sleep(1);
        //2
        System.out.println(ref.getStamp());
        //false;因为此时stamp为2,2!=0;更新失败
        System.out.println(ref.compareAndSet(prev, "C", stamp, stamp + 1));
        //A
        System.out.println(ref.getReference());
    }

    public static void other() throws InterruptedException {
        new Thread(() -> {
            String prev = ref.getReference();
            int stamp = ref.getStamp();
            //0
            System.out.println(stamp);
            //true
            System.out.println(ref.compareAndSet(prev, "B", stamp, stamp + 1));
            //B
            System.out.println(ref.getReference());
        }).start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            String prev = ref.getReference();
            int stamp = ref.getStamp();
            //1
            System.out.println(stamp);
            //true
            System.out.println(ref.compareAndSet(prev, "A", stamp, stamp + 1));
            //A
            System.out.println(ref.getReference());
        }).start();

    }
}
