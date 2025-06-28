package com.elec5620.intelligentfinancial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Entity object for customer database table.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String customerName;

    @OneToOne(mappedBy = "customer")
    private FinancialDetail financialDetail;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites = new ArrayList<>();
    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public void editSavings(double savings) {
        financialDetail.setSavings(savings);
    }

    public void editDebts(double debts) {
        financialDetail.setDebts(debts);
    }

    public void editSalary(double salary) {
        financialDetail.setSalary(salary);
    }

    public void editFixedAssets(double fixedAssets) {
        financialDetail.setFixedAssets(fixedAssets);
    }
}
