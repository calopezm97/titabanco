package com.titamedia.banco.controllers;

import com.titamedia.banco.persistence.entities.TransactionEntity;
import com.titamedia.banco.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {

    @Autowired
    ITransactionService TransactionService;


    @PostMapping("/create")
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionEntity Transaction) {
        try {
            TransactionEntity createdTransaction = TransactionService.createTransaction(Transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TransactionEntity>> getAllTransactions() {
        try {
            List<TransactionEntity> Transactions = TransactionService.getAllTransactions();
            return new ResponseEntity<>(Transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get/{TransactionId}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable Long TransactionId) {
        try {
            Optional<TransactionEntity> Transaction = TransactionService.getTransactionById(TransactionId);
            return Transaction.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{TransactionId}")
    public ResponseEntity<TransactionEntity> updateTransaction(@PathVariable Long TransactionId, @RequestBody TransactionEntity newTransaction) {
        try {
            TransactionEntity updatedTransaction = TransactionService.updateTransaction(TransactionId, newTransaction);
            if (updatedTransaction != null) {
                return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{TransactionId}")
    public ResponseEntity<HashMap<String, String>> deleteTransaction(@PathVariable Long TransactionId) {
        try {
            HashMap<String, String> response = TransactionService.deleteTransaction(TransactionId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
