package ru.rosbank.cache.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("counterparty")
    private CounterpartyDto counterparty;

}
