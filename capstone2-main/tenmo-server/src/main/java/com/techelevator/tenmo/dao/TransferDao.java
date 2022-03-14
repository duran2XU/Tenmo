package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.tenmo.model.TransferType;

import java.util.List;


public interface TransferDao {

    void createTransfer(Transfer transfer);

    List<Transfer> getTransfersByUserId(int userId);

    Transfer getTransferByTransferId(int transferId);

    List<Transfer> getAllTransfers();

    List<Transfer> getPendingTransfers(int userId);

    void updateTransfer(Transfer transfer);

    // create a transfer, accept a transfer, view one transfer, transfer history

//    Transfer createTransfer(long id, int type, int status, String from, String to, BigDecimal amount);
//
//    List<Transfer> viewTransferHistory ( long accountId);
//
//    Transfer viewTransferById ( long transferId);


//    String acceptTransfer ( long transferId, int transferStatusId);

//    void createTransfer(Transfer transfer);
//
//    List<Transfer> getTransfersByUserId(int userId);
//
//    Transfer getTransferByTransferId(int transferId);
//
//    List<Transfer> getAllTransfers();
//
//    List<Transfer> getPendingTransfers(int userId);
//
//    void updateTransfer(Transfer transfer);
//
//    TransferStatus getTransferStatusByDescription(String description);
//
//    TransferStatus getTransferStatusById(int transferStatusId);
//
//    TransferType getTransferTypeFromDescription(String description);
//
//    TransferType getTransferTypeFromId(int transferId);
//
//    // find all authenticated users to transfer to. Once found choose user and send money.


}