package com.titamedia.banco.services.businesslogic;

import com.titamedia.banco.persistence.entities.TransactionEntity;
import com.titamedia.banco.persistence.repos.TransactionRepository;
import com.titamedia.banco.services.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements ITransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionRepository TransactionRepository;

    @Override
    public TransactionEntity createTransaction(TransactionEntity Transaction) {
        try {
            return TransactionRepository.save(Transaction);
        } catch (Exception e) {
            LOGGER.error("Error while creating Transaction: {}", e.getMessage());
            throw new RuntimeException("Error creating Transaction");
        }
    }

    @Override
    public List<TransactionEntity> getAllTransactions() {
        try {
            return TransactionRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all Transactions: {}", e.getMessage());
            throw new RuntimeException("Error fetching Transactions");
        }
    }

    @Override
    public Optional<TransactionEntity> getTransactionById(Long TransactionId) {
        try {
            return TransactionRepository.findById(TransactionId);
        } catch (Exception e) {
            LOGGER.error("Error while fetching Transaction by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching Transaction by ID");
        }
    }

    @Override
    public TransactionEntity updateTransaction(Long TransactionId, TransactionEntity newTransaction) {
        try {
            TransactionEntity existingTransaction = TransactionRepository.findById(TransactionId).orElse(null);
            if (existingTransaction != null) {
                //existingTransaction.setName(newTransaction.getName());

                return TransactionRepository.save(existingTransaction);
            }
            throw new RuntimeException("Transaction not found");
        } catch (Exception e) {
            LOGGER.error("Error while updating Transaction: {}", e.getMessage());
            throw new RuntimeException("Error updating Transaction");
        }
    }

    @Override
    public HashMap<String, String> deleteTransaction(Long TransactionId) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Transaction deleted succesfully!");
            TransactionRepository.deleteById(TransactionId);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error while deleting Transaction: {}", e.getMessage());
            throw new RuntimeException("Error deleting Transaction");
        }
    }
}
