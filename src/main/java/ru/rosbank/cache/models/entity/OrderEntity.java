package ru.rosbank.cache.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    private int orderId;
    private LocalDateTime dateTime;
    private BigDecimal amount;
    private int counterpartyUniqueNumber;

}
