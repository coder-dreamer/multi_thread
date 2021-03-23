package com.lhp.thread.part5;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @author 53137
 */
@Slf4j(topic = "c.TestSimpleDataFormat")
public class TestSimpleDataFormat {
    public static void main(String[] args) {
        //sdf();
        dtf();
    }

    /**
     * 线程安全
     */
    private static void dtf() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                log.debug("{}", dtf.parse("2021-03-20"));
            }).start();
        }
    }

    /**
     * 线程不安全
     */
    private static void sdf() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    log.debug("{}", sdf.parse("2021-03-20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
