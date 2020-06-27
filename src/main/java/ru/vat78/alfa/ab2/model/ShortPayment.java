package ru.vat78.alfa.ab2.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class ShortPayment {
    private Integer categoryId;
    private String userId;
    private String recipientId;
    private Integer amount;

    public static ShortPayment getShortPayment(Payment payment) {
        return ShortPayment.builder()
                .categoryId(payment.getCategoryId())
                .userId(payment.getUserId())
                .recipientId(payment.getRecipientId())
                .amount(payment.getAmount())
                .build();
    }
}
