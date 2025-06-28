package com.elec5620.intelligentfinancial.controller;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.model.Product;
import com.elec5620.intelligentfinancial.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3001")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private AssetAnalysisService assetAnalysisService;

    @Autowired
    private AssetReportService assetReportService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getFinancialProducts() {
        List<Product> products = customerService.getRecommendedProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(products); // 204 No Content
        }
        return ResponseEntity.ok(products); // 200 OK
    }

    @PostMapping("/analyzeAssets/{customerId}")
    public ResponseEntity<AssetAnalysisDTO> analyzeAssets(@PathVariable Long customerId, @RequestBody FinancialDetailDTO financialDetailDTO) {
        financialDetailDTO.setCustomerId(customerId);
        AssetAnalysisDTO assetAnalysis = assetAnalysisService.generateAssetAnalysis(financialDetailDTO);
        if (assetAnalysis == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(assetAnalysis); // 201 Created
    }

    @GetMapping("/getAssetReport/{customerId}")
    public ResponseEntity<AssetReportDTO> getAssetReport(@PathVariable Long customerId) {
        AssetReportDTO assetReport = assetReportService.getReportByCustomerId(customerId);
        if (assetReport == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        return ResponseEntity.ok(assetReport); // 200 OK
    }

    @PostMapping("/chat/{customerId}")
    public ResponseEntity<ChatDTO> chatWithAI(@PathVariable Long customerId, @RequestBody String message) {
        ChatDTO chatResponse = chatService.chatWithOpenAi(customerId, message);
        return ResponseEntity.ok(chatResponse);
    }

    @GetMapping("/chatHistory")
    public ResponseEntity<List<ChatDTO>> getChatHistory(@RequestParam Long customerId) {
        List<ChatDTO> chatHistory = customerService.getChatHistory(customerId);
        if (chatHistory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(chatHistory); // 204 No Content
        }
        return ResponseEntity.ok(chatHistory); // 200 OK
    }

    @GetMapping("/ids-names")
    public ResponseEntity<List<CustomerIdNameDTO>> getAllCustomerIdsAndNames() {
        List<CustomerIdNameDTO> customerIdsAndNames = customerService.getAllCustomerIdsAndNames();
        if (customerIdsAndNames.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customerIdsAndNames); // 204 No Content
        }
        return ResponseEntity.ok(customerIdsAndNames); // 200 OK
    }
}
