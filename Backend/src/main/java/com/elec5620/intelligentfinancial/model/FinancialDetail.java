package com.elec5620.intelligentfinancial.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FinancialDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private Double savings;
    private Double debts;
    private Double salary;
    private Double fixedAssets;

    public FinancialDetail(Customer customer) {
        this.customer = customer;
    }

}
