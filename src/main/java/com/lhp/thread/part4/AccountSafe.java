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
        Account test = new AccountSafeImpl(10000);
        Account.demo(test);
    }
}

class AccountSafeImpl implements Account {
    private Integer balance;

    public AccountSafeImpl(Integer balance) {
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
