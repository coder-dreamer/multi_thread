package com.lhp.thread.part4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 原子更新器
 *
 * @author 53137
 */
public class AtomicReferenceFieldUpdaterTest {
    public static AtomicReferenceFieldUpdater ref =
            AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");

    public static void main(String[] args) throws InterruptedException {
        Student student = new Student();

        new Thread(() -> {
            //true
            System.out.println(ref.compareAndSet(student, null, "list"));
        }).start();
        TimeUnit.SECONDS.sleep(1);
        //false
        System.out.println(ref.compareAndSet(student, null, "张三"));
        //name字段被更新为list
        System.out.println(student);
    }
}

class Student {

    public volatile String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
