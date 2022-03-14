package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao  {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }


    // view current balance
    public Balance getBalance(String username) {
        String sql = "SELECT balance FROM accounts JOIN users ON accounts.user_id = users.user_id WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        Balance balance = new Balance();

        if (results.next()) {
            String accountBalance = results.getString("balance");
            balance.setBalance(new BigDecimal(accountBalance));
        }
        return balance;
    }

    @Override
    public void updateAccount(Account accountToUpdate) {
        String sql = "Update accounts "
                + "Set balance = ? "
                + "Where account_id = ?";
        jdbcTemplate.update(sql, accountToUpdate.getBalance().getBalance(), accountToUpdate.getAccountId());
    }

    @Override
    public Account getAccountByAccountId(int accountId) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);
        Account account = null;
        if(result.next()) {
            account = mapRowToAccounts(result);
        }
        return account;
    }

    @Override
    public Account getAccountByUserId(int userId) {
            String sql = "SELECT account_id, user_id, balance FROM accounts WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
            Account account = null;
            if(result.next()) {
                account = mapRowToAccounts(result);
            }
            return account;
        }


    private Account mapRowToAccounts(SqlRowSet rs){
        int accountId = rs.getInt("account_id");
        int userAccountId = rs.getInt("user_id");

        Balance balance = new Balance();
        String accountBalance = rs.getString("balance");
        balance.setBalance(new BigDecimal(accountBalance));
        return new Account(accountId, userAccountId, balance);
    }
}

//    // get account by user id
//    @Override
//    public Account getAccountByUserID(int userId) {
//        String sql = "SELECT account_id, user_id, balance FROM accounts WHERE user_id = ?";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
//        Account account = null;
//        if(result.next()) {
//            account = mapRowToAccounts(result);
//        }
//        return account;
//    }


//    @Override
//    public List<Account> listAllAccounts() {
//        List<Account> allAccounts = new ArrayList<>();
//        String sqlGetAllDepartments = "SELECT * FROM accounts";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllDepartments);
//        while (results.next()) {
//            Account account = mapRowToAccounts(results);
//            allAccounts.add(account);
//        }
//        return allAccounts;
//
//    }







