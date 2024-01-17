package com.titamedia.banco.controllers;

import com.titamedia.banco.persistence.entities.BankEntity;
import com.titamedia.banco.services.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    IBankService BankService;


    @PostMapping("/create")
    public ResponseEntity<BankEntity> createBank(@RequestBody BankEntity Bank) {
        try {
            BankEntity createdBank = BankService.createBank(Bank);
            return new ResponseEntity<>(createdBank, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/banks")
    public ResponseEntity<List<BankEntity>> getAllBanks() {
        try {
            List<BankEntity> Banks = BankService.getAllBanks();
            return new ResponseEntity<>(Banks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get/{BankId}")
    public ResponseEntity<BankEntity> getBankById(@PathVariable Long BankId) {
        try {
            Optional<BankEntity> Bank = BankService.getBankById(BankId);
            return Bank.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{BankId}")
    public ResponseEntity<BankEntity> updateBank(@PathVariable Long BankId, @RequestBody BankEntity newBank) {
        try {
            BankEntity updatedBank = BankService.updateBank(BankId, newBank);
            if (updatedBank != null) {
                return new ResponseEntity<>(updatedBank, HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{BankId}")
    public ResponseEntity<HashMap<String, String>> deleteBank(@PathVariable Long BankId) {
        try {
            HashMap<String, String> response = BankService.deleteBank(BankId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
