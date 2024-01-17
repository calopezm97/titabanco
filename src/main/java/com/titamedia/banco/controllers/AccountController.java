package com.titamedia.banco.controllers;

import com.titamedia.banco.persistence.entities.AccountEntity;
import com.titamedia.banco.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    IAccountService AccountService;


    @PostMapping("/create")
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity Account) {
        try {
            AccountEntity createdAccount = AccountService.createAccount(Account);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AccountEntity>> getAllAccounts() {
        try {
            List<AccountEntity> Accounts = AccountService.getAllAccounts();
            return new ResponseEntity<>(Accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get/{AccountId}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable Long AccountId) {
        try {
            Optional<AccountEntity> Account = AccountService.getAccountById(AccountId);
            return Account.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{AccountId}")
    public ResponseEntity<AccountEntity> updateAccount(@PathVariable Long AccountId, @RequestBody AccountEntity newAccount) {
        try {
            AccountEntity updatedAccount = AccountService.updateAccount(AccountId, newAccount);
            if (updatedAccount != null) {
                return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{AccountId}")
    public ResponseEntity<HashMap<String, String>> deleteAccount(@PathVariable Long AccountId) {
        try {
            HashMap<String, String> response = AccountService.deleteAccount(AccountId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
