package com.lhp.thread.part4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 此类线程安全
 *
 * @author 53137
 */
public class AccountCas {
    public static void main(String[] args) {
        Account test = new AccountCasImpl(10000);
        Account.demo(test);
    }
}

class AccountCasImpl implements Account {
    private AtomicInteger balance;

    public AccountCasImpl(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withDraw(Integer amount) {
        while (true) {
            //之前的余额
            int prev = balance.get();
            //操作后的余额
            int next = prev - amount;
            //操作成功
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}
