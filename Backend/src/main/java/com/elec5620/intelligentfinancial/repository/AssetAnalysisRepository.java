package com.elec5620.intelligentfinancial.repository;

import com.elec5620.intelligentfinancial.model.AssetAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetAnalysisRepository extends JpaRepository<AssetAnalysis, Long> {
    List<AssetAnalysis> findByCustomerId(Long customerId);
}