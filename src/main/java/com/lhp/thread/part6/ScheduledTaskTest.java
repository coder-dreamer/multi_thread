package com.lhp.thread.part6;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 让每周四18:00:00执行定时任务
 *
 * @author 53137
 */
@Slf4j(topic = "c.ScheduledTaskTest")
public class ScheduledTaskTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        log.debug("当前时间{}", now);
        //周四时间
        LocalDateTime time = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        log.debug("周四时间{}", time);
        //当前时间大于周四，取下周四时间
        if (now.compareTo(time) > 0) {
            time = time.plusWeeks(1);
        }
        long initialDelay = Duration.between(now, time).toMillis();
        //initialDelay---当前时间距离周四的时间间隔
        //period（间隔）-----一周的毫秒
        long period = 1000 * 60 * 60 * 24 * 7;

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.debug("running.......");
        }, initialDelay, period, TimeUnit.MILLISECONDS);
    }
}
