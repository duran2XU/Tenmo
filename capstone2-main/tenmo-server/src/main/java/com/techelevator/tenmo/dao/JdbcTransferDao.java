package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public Transfer createTransfer(long id, int type, int status, String from, String to, BigDecimal amount) {
//        Transfer transfer = new Transfer();
//        String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, " +
//                "transfer_status_id, account_from, account_to, amount) Values (?,?,?,?,?,?)";
//        jdbcTemplate.update(sql, type, status, from, to, amount);
//
//        return transfer;
//    }
//
//    @Override
//    public List<Transfer> viewTransferHistory(long accountId) {
//            List<Transfer> allTransfers = new ArrayList<>();
//            String sql = "SELECT * FROM transfers WHERE account_to = ? OR account_from = ?";
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId, accountId);
//            while (results.next()) {
//                Transfer transfer = mapRowToTransfers(results);
//                allTransfers.add(transfer);
//            }
//
//            return allTransfers;
//
//    }
//
//    @Override
//    public Transfer viewTransferById(long transferId) {
//            Transfer transfer = new Transfer();
//            String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, " +
//                    "account_from, account_to, amount to_date FROM transfers WHERE transfer_id = ? ";
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
//            if (results.next()) {
//                transfer = mapRowToTransfers(results);
//            }
//
//            return transfer;
//    }

//    @Override
//    public TransferStatus getTransferStatusByDesc(String description) {
//        String sql = "SELECT transfer_status_id, transfer_status_desc FROM transfer_statuses WHERE transfer_status_desc = ?";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, description);
//        TransferStatus transferStatus = null;
//        if (result.next()) {
//            int transferStatusID = result.getInt("transfer_status_id");
//            String transferStatusDesc = result.getString("transfer_status_desc");
//            transferStatus = new TransferStatus(transferStatusID, transferStatusDesc);
//
//        }
//
//        return transferStatus;
//    }

//    public TransferStatus getTransferStatusById(int transferStatusId) {
//        String sql = "SELECT transfer_status_id, transfer_status_desc FROM transfer_statuses WHERE transfer_status_id = ?";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferStatusId);
//        TransferStatus transferStatus = null;
//        if(result.next()) {
//            int transferStatusId2 = result.getInt("transfer_status_id");
//            String transferStatusDesc = result.getString(("transfer_status_desc"));
//            transferStatus = new TransferStatus(transferStatusId2, transferStatusDesc);
//
//        }
//
//        return transferStatus;
//    }
//    @Override
//    public TransferType getTransferTypeFromDescription(String description) {
//        String sql = "SELECT transfer_type_id, transfer_type_desc FROM transfer_types WHERE transfer_type_desc = ?";
//
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, description);
//        TransferType transferType = null;
//
//        if(result.next()) {
//            int transferTypeId = result.getInt("transfer_type_id");
//            String transferTypeDescription = result.getString("transfer_type_desc");
//
//            transferType = new TransferType(transferTypeId, transferTypeDescription);
//        }
//        return transferType;
//    }

//    @Override
//    public TransferType getTransferTypeFromId(int transferId) {
//        String sql = "SELECT transfer_type_id, transfer_type_desc FROM transfer_types WHERE transfer_type_id = ?";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
//
//        TransferType transferType = null;
//        if(result.next()) {
//
//            int transferTypeId = result.getInt("transfer_type_id");
//            String transferTypeDesc = result.getString("transfer_type_desc");
//
//            transferType = new TransferType(transferTypeId, transferTypeDesc);
//        }
//
//
//        return transferType;
//    }

    @Override
    public void createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }


    @Override
    public List<Transfer> getTransfersByUserId(int userId) {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfers " +
                "JOIN accounts ON accounts.account_id = transfers.account_from OR accounts.account_id = transfers.account_to " +
                "WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        List<Transfer> transfers = new ArrayList<>();

        while(results.next()) {
            transfers.add(mapRowToTransfers(results));
        }

        return transfers;
    }

    @Override
    public Transfer getTransferByTransferId(int transferId) {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfers WHERE transfer_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        Transfer transfer = null;

        if(result.next()){
            transfer = mapRowToTransfers(result);
        }

        return transfer;
    }

    @Override
    public List<Transfer> getAllTransfers() {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfers";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Transfer> transfers = new ArrayList<>();

        while(results.next()){
            transfers.add(mapRowToTransfers(results));
        }

        return transfers;
    }


    @Override
    public List<Transfer> getPendingTransfers(int userId) {
        String sql = "SELECT transfer_id, transfer_type_id, transfers.transfer_status_id, account_from, account_to, amount " +
                "FROM transfers " +
                "JOIN accounts ON accounts.account_id = transfers.account_from " +
                "JOIN transfer_statuses ON transfers.transfer_status_id = transfer_statuses.transfer_status_id " +
                "WHERE user_id = ? AND transfer_status_desc = 'Pending'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        List<Transfer> transfers = new ArrayList<>();

        while(results.next()) {
            transfers.add(mapRowToTransfers(results));
        }
        return transfers;
    }

    @Override
    public void updateTransfer(Transfer transfer) {

        String sql = "UPDATE transfers " +
                "SET transfer_status_id = ? " +
                "WHERE transfer_id = ?";

        jdbcTemplate.update(sql, transfer.getTransferStatusId(), transfer.getTransferId());
    }




    private Transfer mapRowToTransfers(SqlRowSet results) {
        int transferId = results.getInt("transfer_id");
        int transferTypeId = results.getInt("transfer_type_id");
        int transferStatusId = results.getInt("transfer_status_id");
        int accountFrom = results.getInt("account_from");
        int accountTo = results.getInt("account_to");
        String amountDouble = results.getString("amount");

        Transfer transfer = new Transfer(transferId, transferTypeId, transferStatusId, accountFrom, accountTo, new BigDecimal(amountDouble));
        return transfer;

    }
}


