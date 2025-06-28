package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.model.*;
import com.elec5620.intelligentfinancial.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialDetailService {
    @Autowired
    private FinancialDetailRepository financialDetailRepository;

    public FinancialDetailDTO toDTO(FinancialDetail financialDetail) {
        FinancialDetailDTO dto = new FinancialDetailDTO();
        dto.setSavings(financialDetail.getSavings());
        dto.setDebts(financialDetail.getDebts());
        dto.setSalary(financialDetail.getSalary());
        dto.setFixedAssets(financialDetail.getFixedAssets());
        return dto;
    }

    public FinancialDetail toEntity(FinancialDetailDTO dto, Customer customer) {
        FinancialDetail financialDetail = new FinancialDetail(customer);
        financialDetail.setSavings(dto.getSavings());
        financialDetail.setDebts(dto.getDebts());
        financialDetail.setSalary(dto.getSalary());
        financialDetail.setFixedAssets(dto.getFixedAssets());
        return financialDetail;
    }
}
