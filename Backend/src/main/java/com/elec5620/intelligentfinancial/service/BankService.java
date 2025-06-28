package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.BankDTO;
import com.elec5620.intelligentfinancial.model.Bank;
import com.elec5620.intelligentfinancial.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankDTO> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        return banks.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public BankDTO getBankById(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        return bank.map(this::toDTO).orElse(null);
    }

    public BankDTO createBank(BankDTO bankDTO) {
        Bank bank = toEntity(bankDTO);
        Bank savedBank = bankRepository.save(bank);
        return toDTO(savedBank);
    }

    public BankDTO updateBank(Long id, BankDTO bankDTO) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found"));
        bank.setBankName(bankDTO.getBankName());
        Bank updatedBank = bankRepository.save(bank);
        return toDTO(updatedBank);
    }

    public void deleteBank(Long id) {
        if (!bankRepository.existsById(id)) {
            throw new RuntimeException("Bank not found");
        }
        bankRepository.deleteById(id);
    }

    private BankDTO toDTO(Bank bank) {
        BankDTO dto = new BankDTO();
        dto.setId(bank.getId());
        dto.setBankName(bank.getBankName());
        return dto;
    }

    private Bank toEntity(BankDTO dto) {
        Bank bank = new Bank();
        bank.setBankName(dto.getBankName());
        return bank;
    }
}
