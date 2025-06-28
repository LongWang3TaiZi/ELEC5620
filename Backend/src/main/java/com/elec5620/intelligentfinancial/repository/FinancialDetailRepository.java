package com.elec5620.intelligentfinancial.repository;

import com.elec5620.intelligentfinancial.model.FinancialDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDetailRepository extends JpaRepository<FinancialDetail, Long> {
}
