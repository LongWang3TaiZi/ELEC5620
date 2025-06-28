package com.elec5620.intelligentfinancial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.elec5620.intelligentfinancial.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerById(Long id);
}
