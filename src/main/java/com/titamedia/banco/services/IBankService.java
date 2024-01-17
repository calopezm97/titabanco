package com.titamedia.banco.services;


import com.titamedia.banco.persistence.entities.BankEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IBankService {
    public BankEntity createBank(BankEntity Bank);
    public List<BankEntity> getAllBanks();
    public Optional<BankEntity> getBankById(Long BankId);
    public BankEntity updateBank(Long BankId, BankEntity newBank);
    public HashMap<String, String> deleteBank(Long BankId);
}
