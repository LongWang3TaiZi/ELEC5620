package com.elec5620.intelligentfinancial.controller;

import com.elec5620.intelligentfinancial.dto.BankDTO;
import com.elec5620.intelligentfinancial.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@CrossOrigin(origins = "http://localhost:3001")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ResponseEntity<List<BankDTO>> getAllBanks() {
        List<BankDTO> banks = bankService.getAllBanks();
        return ResponseEntity.ok(banks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankDTO> getBankById(@PathVariable Long id) {
        BankDTO bank = bankService.getBankById(id);
        return ResponseEntity.ok(bank);
    }

    @PostMapping
    public ResponseEntity<BankDTO> createBank(@RequestBody BankDTO bankDTO) {
        BankDTO createdBank = bankService.createBank(bankDTO);
        return new ResponseEntity<>(createdBank, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDTO> updateBank(@PathVariable Long id, @RequestBody BankDTO bankDTO) {
        BankDTO updatedBank = bankService.updateBank(id, bankDTO);
        return ResponseEntity.ok(updatedBank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }
}
