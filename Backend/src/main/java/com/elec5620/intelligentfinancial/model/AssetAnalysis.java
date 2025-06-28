package com.elec5620.intelligentfinancial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AssetAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long customerId;
    @Lob  // This annotation tells JPA to use a large object type (e.g., TEXT in SQL)
    @Column(columnDefinition = "TEXT")  // Explicitly setting the column type as TEXT for clarity
    private String analysisDetails;
    private LocalDateTime generatedAt;
}
