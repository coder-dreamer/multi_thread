package com.lhp.thread.part6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * fork-join框架(计算 10的阶乘)
 *
 * @author 53137
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        System.out.println(pool.invoke(new MyTask(10)));
    }
}

class MyTask extends RecursiveTask<Integer> {
    int n = 0;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            return 1;
        }
        MyTask task = new MyTask(n - 1);
        //线程去执行任务
        task.fork();
        //获取执行结果
        Integer result = task.join();
        //执行逻辑
        return n + result;
    }
}