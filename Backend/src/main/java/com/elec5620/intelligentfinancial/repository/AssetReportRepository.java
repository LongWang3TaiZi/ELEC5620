package com.elec5620.intelligentfinancial.repository;

import com.elec5620.intelligentfinancial.model.AssetReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetReportRepository extends JpaRepository<AssetReport, Long> {
    Optional<AssetReport> findByCustomerId(Long customerId);
}