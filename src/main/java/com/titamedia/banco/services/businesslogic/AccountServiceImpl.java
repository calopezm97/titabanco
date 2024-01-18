package com.titamedia.banco.services.businesslogic;

import com.titamedia.banco.persistence.entities.AccountEntity;
import com.titamedia.banco.persistence.entities.BankEntity;
import com.titamedia.banco.persistence.repos.AccountRepository;
import com.titamedia.banco.services.IAccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
    EntityManager em;


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
    public List<AccountEntity> findByUser(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AccountEntity> cq = cb.createQuery(AccountEntity.class);

        Root<AccountEntity> account = cq.from(AccountEntity.class);
        Predicate userIdPredicate = cb.equal(account.get("user_id"), userId);
        cq.where(userIdPredicate);

        TypedQuery<AccountEntity> query = em.createQuery(cq);
        return query.getResultList();
    }
}
