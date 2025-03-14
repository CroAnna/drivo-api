package org.croanna.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.croanna.dtos.ChartDatasetResponseDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChartResponse {
    List<String> labels;
    List<ChartDatasetResponseDTO> datasets;
}
