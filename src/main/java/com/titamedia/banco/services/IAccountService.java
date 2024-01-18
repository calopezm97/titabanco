package com.titamedia.banco.services;


import com.titamedia.banco.persistence.entities.AccountEntity;
import com.titamedia.banco.persistence.entities.BankEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public AccountEntity createAccount(AccountEntity Account);
    public List<AccountEntity> getAllAccounts();
    public Optional<AccountEntity> getAccountById(Long AccountId);
    public List<AccountEntity> findByUser(Long userId);

}
