package ru.vat78.alfa.ab2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStats {
    int oftenCategoryId;
    int rareCategoryId;
    int maxAmountCategoryId;
    int minAmountCategoryId;
}
