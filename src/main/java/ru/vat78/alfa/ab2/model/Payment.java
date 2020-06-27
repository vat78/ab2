package ru.vat78.alfa.ab2.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String ref;
    private Integer categoryId;
    private String userId;
    private String recipientId;
    private String desc;
    private Integer amount;

    public static Optional<Payment> buildPayment(String jsonString) {
        try {
            JsonNode raw = new ObjectMapper().readTree(jsonString);
            return Optional.of(Payment.builder()
                    .ref(raw.get("ref").asText())
                    .categoryId(raw.get("categoryId").asInt())
                    .userId(raw.get("userId").asText())
                    .recipientId(raw.get("recipientId").asText())
                    .desc(raw.get("desc").asText())
                    .amount(Integer.valueOf(raw.get("amount").asText().replaceFirst("\\.", "")))
                    .build());

        } catch (JsonProcessingException ignored) {
            return Optional.empty();
        }
    }
}
