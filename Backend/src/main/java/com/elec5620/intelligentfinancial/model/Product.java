package com.elec5620.intelligentfinancial.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
@Data  // 自动生成所有 getter/setter、构造器等
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String supplierName;
    private String productInfo;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
}
