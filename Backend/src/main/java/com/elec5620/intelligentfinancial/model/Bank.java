package com.elec5620.intelligentfinancial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bank")
@Getter
@Setter
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bankname", nullable = false)
    private String bankName;

    public Bank(String bankName) {
        this.bankName = bankName;
    }
}
