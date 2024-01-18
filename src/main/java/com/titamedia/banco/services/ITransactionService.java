package com.titamedia.banco.services;


import com.titamedia.banco.persistence.entities.TransactionEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    public TransactionEntity createTransaction(TransactionEntity Transaction);
    public List<TransactionEntity> getAllTransactions();
    public Optional<TransactionEntity> getTransactionById(Long TransactionId);
}
