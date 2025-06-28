package com.elec5620.intelligentfinancial.dto;

import com.elec5620.intelligentfinancial.model.FinancialDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FinancialDetailDTO {
    private Long customerId;
    private Double savings;
    private Double debts;
    private Double salary;
    private Double fixedAssets;

    /**
     * Constructs an FinancialDetailDTO from a FinancialDetail entity.
     *
     * @param financialDetail the financial detail entity
     */
    public FinancialDetailDTO toDTO(FinancialDetail financialDetail) {
        if (financialDetail == null) {
            // Return an empty DTO to handle the null case
            return new FinancialDetailDTO(); // Or set default values if needed
        }

        FinancialDetailDTO dto = new FinancialDetailDTO();
        dto.setSavings(financialDetail.getSavings());
        dto.setDebts(financialDetail.getDebts());
        dto.setSalary(financialDetail.getSalary());
        dto.setFixedAssets(financialDetail.getFixedAssets());
        return dto;
    }

}
