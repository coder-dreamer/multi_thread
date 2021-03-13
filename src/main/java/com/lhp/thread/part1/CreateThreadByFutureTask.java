package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程
 *
 * @author 53137
 */
@Slf4j(topic = "c.CreateThreadByFutureTask")
public class CreateThreadByFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建任务
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("hello");
            Thread.sleep(1000);
            return 100;
        });
        //执行任务
        new Thread(task, "t1").start();
        //主线程阻塞等待，获取结果
        log.debug("{}", task.get());
    }
}
