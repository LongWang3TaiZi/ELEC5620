package com.elec5620.intelligentfinancial.controller;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/{customerId}/asset")
@CrossOrigin(origins = "http://localhost:3002")
public class AssetController {
    @Autowired
    private AssetAnalysisService assetAnalysisService;

    @Autowired
    private AssetReportService assetReportService;

    @PostMapping("/generate-analysis")
    public ResponseEntity<AssetAnalysisDTO> createAssetAnalysis(@PathVariable Long customerId, @RequestBody FinancialDetailDTO dto) {
        dto.setCustomerId(customerId);
        try {
            AssetAnalysisDTO createdAnalysis = assetAnalysisService.generateAssetAnalysis(dto);
            return new ResponseEntity<>(createdAnalysis, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/get-report")
    public ResponseEntity<AssetReportDTO> getAssetReport(@PathVariable Long customerId) {
        AssetReportDTO report = assetReportService.getReportByCustomerId(customerId);
        if (report != null) {
            return ResponseEntity.ok(report);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
