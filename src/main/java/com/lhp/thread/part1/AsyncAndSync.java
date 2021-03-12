package com.lhp.thread.part1;

import com.lhp.thread.constants.Constants;
import com.lhp.thread.util.FileReader;
import lombok.extern.slf4j.Slf4j;


/**
 * 同步和异步
 *
 * @author 53137
 */
@Slf4j(topic = "c.AsyncAndSync")
public class AsyncAndSync {
    public static void main(String[] args) {
        //AsyncAndSync.sync();
        AsyncAndSync.aSync();
    }

    /**
     * 异步执行
     */
    public static void aSync() {
        new Thread(() -> {
            FileReader.read(Constants.MP4_FULL_PATH);
        }).start();
        log.debug("做其他事情");
    }

    /**
     * 同步执行
     */
    public static void sync() {
        FileReader.read(Constants.MP4_FULL_PATH);
        log.debug("做其他事情");
    }

}
