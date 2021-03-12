package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程
 *
 * @author 53137
 */
@Slf4j(topic = "c.CreateThread")
public class CreateThreadByLambda {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            log.debug("使用lambda表达式创建线程");
        },"t1");
        t.start();
        log.debug("主线程");
    }
}
