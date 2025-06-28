package com.elec5620.intelligentfinancial.dto;

import com.elec5620.intelligentfinancial.model.*;
import lombok.Getter;
import lombok.*;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String customerName;
    @Getter
    @Setter
    private FinancialDetailDTO financialDetail;

    /**
     * Constructs a CustomerDTO from a Customer entity.
     *
     * @param customerEntity the customer entity
     */
    public CustomerDTO(Customer customerEntity) {
        this.id = customerEntity.getId();
        this.customerName = customerEntity.getCustomerName();
    }

}
