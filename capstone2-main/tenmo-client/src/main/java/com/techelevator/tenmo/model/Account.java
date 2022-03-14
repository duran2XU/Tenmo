package com.techelevator.tenmo.model;


public class Account {

    public Balance balance;
    private int userId;
    private int accountId;


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountID) {
        this.accountId = accountID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
