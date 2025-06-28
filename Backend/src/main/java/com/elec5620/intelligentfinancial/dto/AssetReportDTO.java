package com.elec5620.intelligentfinancial.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class AssetReportDTO {
    private List<AssetAnalysisDTO> history = new ArrayList<>();
}
