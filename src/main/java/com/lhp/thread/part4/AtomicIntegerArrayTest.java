package com.lhp.thread.part4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 原子数组
 *
 * @author 53137
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) throws InterruptedException {
        //[8924, 8918, 8878, 8842, 8917, 8855, 8841, 8844, 8848, 8846]
        //证明线程不安全
        demo(
                () -> new int[10],
                (array) -> array.length,
                (array, index) -> array[index]++,
                (array) -> System.out.println(Arrays.toString(array))
        );
        TimeUnit.SECONDS.sleep(1);
        //[10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000]
        //证明线程安全
        demo(
                () -> new AtomicIntegerArray(10),
                (array) -> array.length(),
                (array, index) -> array.getAndIncrement(index),
                (array) -> System.out.println(array)
        );
    }

    private static <T> void demo(
            //生产者
            Supplier<T> arraySupplier,
            //函数
            Function<T, Integer> lengthFun,
            //消费者，可以接收两个参数
            BiConsumer<T, Integer> putConsumer,
            //消费者，仅能接收一个参数，产生一个结果
            Consumer<T> printConsumer) {
        // 创建集合
        ArrayList<Thread> list = new ArrayList<>();
        // 获取数组
        T array = arraySupplier.get();
        // 获取数组的长度
        int length = lengthFun.apply(array);
        for (int i = 0; i < length; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j % length);
                }
            }));
        }
        list.forEach(Thread::start);
        list.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        printConsumer.accept(array);
    }
}
