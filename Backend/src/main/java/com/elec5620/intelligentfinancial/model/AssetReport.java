package com.elec5620.intelligentfinancial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class AssetReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_report_id")
    private List<AssetAnalysis> history = new ArrayList<>();

    // Method to add analysis to history
    public void addAnalysis(AssetAnalysis analysis) {
        this.history.add(analysis);
    }
}
