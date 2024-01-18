package com.titamedia.banco.controllers;

import com.titamedia.banco.persistence.entities.AccountEntity;
import com.titamedia.banco.services.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    IAccountService AccountService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity Account) {
        try {
            logger.info("Creando cuenta con usuario: {} y banco: {}", Account.getUser(), Account.getBank());
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

    @GetMapping("get/user/{UserId}")
    public ResponseEntity<AccountEntity> getUserById(@PathVariable Long UserId) {
        try {
            List<AccountEntity> accounts = AccountService.findByUser(UserId);

            if (!accounts.isEmpty()) {
                return ResponseEntity.ok((AccountEntity) accounts);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
