package com.elec5620.intelligentfinancial.repository;

import com.elec5620.intelligentfinancial.model.Bank;
import com.elec5620.intelligentfinancial.model.Chat;
import com.elec5620.intelligentfinancial.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findBankById(Long id);
}
