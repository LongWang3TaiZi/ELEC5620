package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.model.*;
import com.elec5620.intelligentfinancial.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetReportService {
    @Autowired
    private AssetReportRepository assetReportRepository;

    public void addAnalysisToReport(Long customerId, AssetAnalysis analysis) {
        Optional<AssetReport> report = assetReportRepository.findByCustomerId(customerId);
        AssetReport assetReport;
        if (!report.isPresent()) {
            assetReport = report.get();
        } else {
            assetReport = new AssetReport();
        }
        assetReport.addAnalysis(analysis);
        assetReportRepository.save(assetReport);
    }

    public AssetReportDTO getReportByCustomerId(Long customerId) {
        Optional<AssetReport> report = assetReportRepository.findByCustomerId(customerId);
        AssetReportDTO reportDTO = new AssetReportDTO();
        if (report.isPresent()) {
            reportDTO.setHistory(report.get().getHistory().stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList()));
            return reportDTO;
        }
        return reportDTO;
    }

    private AssetAnalysisDTO toDTO(AssetAnalysis assetAnalysis) {
        AssetAnalysisDTO dto = new AssetAnalysisDTO();
        dto.setId(assetAnalysis.getId());
        dto.setCustomerId(assetAnalysis.getCustomerId());
        dto.setAnalysisDetails(assetAnalysis.getAnalysisDetails());
        dto.setGeneratedAt(assetAnalysis.getGeneratedAt());
        return dto;
    }

}
