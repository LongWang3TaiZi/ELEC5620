package com.elec5620.intelligentfinancial.dto;

import com.elec5620.intelligentfinancial.model.Bank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankDTO {

    private Long id;
    private String bankName;

    public BankDTO(Bank bankEntity) {
        this.id = bankEntity.getId();
        this.bankName = bankEntity.getBankName();
    }
}
