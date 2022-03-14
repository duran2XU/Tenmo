package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;


public interface AccountDao {

//    Account updateBalance(Account accountToUpdate);
//
//    List<Account> listAllAccounts();
//
//    Account getAccountByUserId(Long userId);
//
//    Account getAccountByAccountId(long accountId);

    Balance getBalance(String username);
    Account getAccountByAccountId(int accountId);
    Account getAccountByUserId(int userid);
    void updateAccount(Account accountToUpdate);
}


