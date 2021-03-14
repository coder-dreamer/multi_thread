package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author 53137
 * 同步模式之保护性暂停升级版
 */
@Slf4j(topic = "c.GuardedSuspensionV2")
public class GuardedSuspensionV2 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        Thread.sleep(2000);
        for (Integer id : Mailboxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }
    }
}

@Slf4j(topic = "c.GuardedSuspensionPlus")
class GuardedSuspensionPlus {
    private Object response;
    private final Object lock = new Object();

    // 标识 Guarded Object
    private int id;

    public GuardedSuspensionPlus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * 获取结果：带超时时间
     *
     * @return
     */
    public Object get(long millis) {
        synchronized (lock) {
            // 1) 记录最初时间
            long begin = System.currentTimeMillis();
            // 2) 已经经历的时间
            long timePassed = 0;
            // 条件不满足则等待
            while (response == null) {
                long waitTime = millis - timePassed;
                log.debug("waitTime: {}", waitTime);
                if (waitTime <= 0) {
                    log.debug("break...");
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 3) 如果提前被唤醒，这时已经经历的时间假设为 400
                timePassed = System.currentTimeMillis() - begin;
            }

            return response;
        }
    }

    /**
     * 生成结果
     *
     * @param response
     */
    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，通知等待线程
            this.response = response;
            lock.notifyAll();
        }
    }
}

class Mailboxes {
    private static Map<Integer, GuardedSuspensionPlus> boxes = new Hashtable<>();
    private static int id = 1;

    // 产生唯一 id
    private static synchronized int generateId() {
        return id++;
    }

    public static GuardedSuspensionPlus getGuardedObject(int id) {
        return boxes.remove(id);
    }

    public static GuardedSuspensionPlus createGuardedObject() {
        GuardedSuspensionPlus go = new GuardedSuspensionPlus(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

@Slf4j(topic = "c.People")
class People extends Thread {
    @Override
    public void run() {
        // 收信
        GuardedSuspensionPlus guardedObject = Mailboxes.createGuardedObject();
        log.debug("开始收信 id:{}", guardedObject.getId());
        Object mail = guardedObject.get(5000);
        log.debug("收到信 id:{}, 内容:{}", guardedObject.getId(), mail);
    }
}

@Slf4j(topic = "c.Postman")
class Postman extends Thread {
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedSuspensionPlus guardedObject = Mailboxes.getGuardedObject(id);
        log.debug("送信 id:{}, 内容:{}", id, mail);
        guardedObject.complete(mail);
    }
}