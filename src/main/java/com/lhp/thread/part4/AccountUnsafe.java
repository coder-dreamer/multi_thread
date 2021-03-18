package com.lhp.thread.part4;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类线程不安全
 *
 * @author 53137
 */
public class AccountUnsafe {
    public static void main(String[] args) {
        Account test = new AccountImpl(10000);
        Account.demo(test);
    }
}

interface Account {

    /**
     * 获取金额的方法
     *
     * @return
     */
    Integer getBalance();

    /**
     * 取款的方法
     *
     * @param amount
     */
    void withDraw(Integer amount);

    /**
     * @param account
     */
    static void demo(Account account) {
        List<Thread> list = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(() -> {
                account.withDraw(10);
            }));
        }
        list.forEach(Thread::start);
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance()
                + " cost: " + (end - start) / 1000_000 + " ms");
    }
}

class AccountImpl implements Account {
    private Integer balance;

    public AccountImpl(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void withDraw(Integer amount) {
        balance -= amount;
    }
}
