package ru.vat78.alfa.ab2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentTemplate {
    private String recipientId;
    private int categoryId;
    private float amount;
}
