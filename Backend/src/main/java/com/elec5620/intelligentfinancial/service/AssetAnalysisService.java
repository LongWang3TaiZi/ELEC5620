package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.model.*;
import com.elec5620.intelligentfinancial.repository.*;
import com.elec5620.intelligentfinancial.utils.OpenAIAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssetAnalysisService {
    @Autowired
    private AssetAnalysisRepository assetAnalysisRepository;

    @Autowired
    private AssetReportService assetReportService;

    public AssetAnalysisDTO toDTO(AssetAnalysis assetAnalysis) {
        AssetAnalysisDTO dto = new AssetAnalysisDTO();
        dto.setId(assetAnalysis.getId());
        dto.setCustomerId(assetAnalysis.getCustomerId());
        dto.setAnalysisDetails(assetAnalysis.getAnalysisDetails());
        dto.setGeneratedAt(assetAnalysis.getGeneratedAt());
        return dto;
    }

    public AssetAnalysis toEntity(AssetAnalysisDTO dto) {
        AssetAnalysis assetAnalysis = new AssetAnalysis();
        assetAnalysis.setCustomerId(dto.getCustomerId());
        assetAnalysis.setAnalysisDetails(dto.getAnalysisDetails());
        assetAnalysis.setGeneratedAt(dto.getGeneratedAt());
        return assetAnalysis;
    }

    public AssetAnalysisDTO generateAssetAnalysis(FinancialDetailDTO financialDetailDTO) {
        String prompt = "Generate a 50-word analysis report based on the asset information I give you. Asset information：" +
                "savings: " + financialDetailDTO.getSavings() +
                ", debts: " + financialDetailDTO.getDebts() +
                ", salary: " + financialDetailDTO.getSalary() +
                ", fixed assets: " + financialDetailDTO.getFixedAssets();
        String analysisDetails = OpenAIAPI.chat(prompt);

        AssetAnalysis assetAnalysis = new AssetAnalysis();
        assetAnalysis.setCustomerId(financialDetailDTO.getCustomerId());
        assetAnalysis.setAnalysisDetails(analysisDetails);
        assetAnalysis.setGeneratedAt(LocalDateTime.now());

        assetAnalysisRepository.save(assetAnalysis);
//        assetReportService.addAnalysisToReport(financialDetailDTO.getCustomerId(), assetAnalysis);

        return toDTO(assetAnalysis);

    }

    public List<AssetAnalysisDTO> getAnalysisByCustomerId(Long customerId) {
        List<AssetAnalysis> analyses = assetAnalysisRepository.findByCustomerId(customerId);
        return analyses.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
