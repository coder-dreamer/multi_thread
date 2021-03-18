package com.lhp.thread.part4;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类线程安全
 *
 * @author 53137
 */
public class AccountSafe {
    public static void main(String[] args) {
        AccountS test = new AccountSImpl(10000);
        AccountS.demo(test);
    }
}

interface AccountS {

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
    static void demo(AccountS account) {
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

class AccountSImpl implements AccountS {
    private Integer balance;

    public AccountSImpl(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void withDraw(Integer amount) {
        synchronized (this) {
            balance -= amount;
        }
    }
}
