package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 生产者和消费者
 *
 * @author 53137
 */
@Slf4j(topic = "c.ProducerAndConsumer")
public class ProducerAndConsumer {
    public static void main(String[] args) {
        MessageList queue = new MessageList(2);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        queue.put(new Message(id, "消息" + id));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者" + i).start();
        }
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    Message message = queue.take();
                    log.debug("消息{}", message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费者").start();
    }
}

/**
 * 消息
 */
@Slf4j(topic = "c.Message")
class Message {
    private int id;
    private String msg;

    public Message(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }
}

/**
 * 消息队列
 */
@Slf4j(topic = "c.MessageList")
class MessageList {

    /**
     * 存放消息的队列
     */
    private LinkedList<Message> queue;
    /**
     * 容量
     */
    private int capacity;

    public MessageList(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    /**
     * 获取消息
     *
     * @return
     */
    public Message take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                log.debug("没有消息");
                queue.wait();
            }
            Message message = queue.removeFirst();
            log.debug("唤醒生产者开始生产消息");
            queue.notifyAll();
            return message;
        }
    }

    /**
     * 添加消息至队列
     *
     * @param message
     * @throws InterruptedException
     */
    public void put(Message message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() >= capacity) {
                log.debug("队列已满");
                queue.wait();
            }
            log.debug("唤醒消费者消费消息");
            queue.addLast(message);
            queue.notifyAll();
        }
    }
}