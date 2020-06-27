package ru.vat78.alfa.ab2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnalyticData {
    private final float min;
    private final float max;
    private final float sum;
}
