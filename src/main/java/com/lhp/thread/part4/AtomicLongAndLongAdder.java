package com.lhp.thread.part4;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 原子累加器
 *
 * @author 53137
 */
public class AtomicLongAndLongAdder {
    public static void main(String[] args) {
        //2000000 cost:51
        //2000000 cost:66
        //2000000 cost:54
        //2000000 cost:53
        //2000000 cost:53
        for (int i = 0; i < 5; i++) {
            demo(() -> new AtomicLong(0), (ref) -> ref.getAndIncrement());
        }
        //2000000 cost:20
        //2000000 cost:9
        //2000000 cost:15
        //2000000 cost:9
        //2000000 cost:9
        for (int i = 0; i < 5; i++) {
            demo(() -> new LongAdder(), (ref) -> ref.increment());
        }
    }

    private static <T> void demo(Supplier<T> supplier, Consumer<T> consumer) {
        ArrayList<Thread> list = new ArrayList<>();

        T adder = supplier.get();
        // 4 个线程，每人累加 50 万
        for (int i = 0; i < 4; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < 500000; j++) {
                    consumer.accept(adder);
                }
            }));
        }
        long start = System.nanoTime();
        list.forEach(t -> t.start());
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(adder + " cost:" + (end - start) / 1000_000);
    }
}
