package com.titamedia.banco.services.businesslogic;

import com.titamedia.banco.persistence.entities.AccountEntity;
import com.titamedia.banco.persistence.repos.AccountRepository;
import com.titamedia.banco.services.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountRepository AccountRepository;

    @Override
    public AccountEntity createAccount(AccountEntity Account) {
        try {
            return AccountRepository.save(Account);
        } catch (Exception e) {
            LOGGER.error("Error while creating Account: {}", e.getMessage());
            throw new RuntimeException("Error creating Account");
        }
    }

    @Override
    public List<AccountEntity> getAllAccounts() {
        try {
            return AccountRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all Accounts: {}", e.getMessage());
            throw new RuntimeException("Error fetching Accounts");
        }
    }

    @Override
    public Optional<AccountEntity> getAccountById(Long AccountId) {
        try {
            return AccountRepository.findById(AccountId);
        } catch (Exception e) {
            LOGGER.error("Error while fetching Account by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching Account by ID");
        }
    }

    @Override
    public AccountEntity updateAccount(Long AccountId, AccountEntity newAccount) {
        try {
            AccountEntity existingAccount = AccountRepository.findById(AccountId).orElse(null);
            if (existingAccount != null) {
                //existingAccount.setName(newAccount.getName());

                return AccountRepository.save(existingAccount);
            }
            throw new RuntimeException("Account not found");
        } catch (Exception e) {
            LOGGER.error("Error while updating Account: {}", e.getMessage());
            throw new RuntimeException("Error updating Account");
        }
    }

    @Override
    public HashMap<String, String> deleteAccount(Long AccountId) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Account deleted succesfully!");
            AccountRepository.deleteById(AccountId);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error while deleting Account: {}", e.getMessage());
            throw new RuntimeException("Error deleting Account");
        }
    }
}
