package com.elec5620.intelligentfinancial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class AssetAnalysisDTO {

    private Long id;
    private Long customerId;
    private String analysisDetails;
    private LocalDateTime generatedAt;
}
