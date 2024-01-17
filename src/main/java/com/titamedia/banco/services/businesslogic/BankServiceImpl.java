package com.titamedia.banco.services.businesslogic;

import com.titamedia.banco.persistence.entities.BankEntity;
import com.titamedia.banco.persistence.repos.BankRepository;
import com.titamedia.banco.services.IBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements IBankService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    BankRepository BankRepository;

    @Override
    public BankEntity createBank(BankEntity Bank) {
        try {
            return BankRepository.save(Bank);
        } catch (Exception e) {
            LOGGER.error("Error while creating Bank: {}", e.getMessage());
            throw new RuntimeException("Error creating Bank");
        }
    }

    @Override
    public List<BankEntity> getAllBanks() {
        try {
            return BankRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all Banks: {}", e.getMessage());
            throw new RuntimeException("Error fetching Banks");
        }
    }

    @Override
    public Optional<BankEntity> getBankById(Long BankId) {
        try {
            return BankRepository.findById(BankId);
        } catch (Exception e) {
            LOGGER.error("Error while fetching Bank by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching Bank by ID");
        }
    }

    @Override
    public BankEntity updateBank(Long BankId, BankEntity newBank) {
        try {
            BankEntity existingBank = BankRepository.findById(BankId).orElse(null);
            if (existingBank != null) {
                existingBank.setName(newBank.getName());

                return BankRepository.save(existingBank);
            }
            throw new RuntimeException("Bank not found");
        } catch (Exception e) {
            LOGGER.error("Error while updating Bank: {}", e.getMessage());
            throw new RuntimeException("Error updating Bank");
        }
    }

    @Override
    public HashMap<String, String> deleteBank(Long BankId) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Bank deleted succesfully!");
            BankRepository.deleteById(BankId);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error while deleting Bank: {}", e.getMessage());
            throw new RuntimeException("Error deleting Bank");
        }
    }
}
