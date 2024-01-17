package com.titamedia.banco.services;


import com.titamedia.banco.persistence.entities.AccountEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public AccountEntity createAccount(AccountEntity Account);
    public List<AccountEntity> getAllAccounts();
    public Optional<AccountEntity> getAccountById(Long AccountId);
    public AccountEntity updateAccount(Long AccountId, AccountEntity newAccount);
    public HashMap<String, String> deleteAccount(Long AccountId);
}
