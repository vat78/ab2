package ru.vat78.alfa.ab2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class UserAnalytic {
    private final String userId;
    private final float totalSum;
    private final Map<Integer, AnalyticData> analyticInfo;
}
