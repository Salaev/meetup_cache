package ru.rosbank.cache.mapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rosbank.cache.models.dto.OrderDto;
import ru.rosbank.cache.models.entity.CounterpartyEntity;
import ru.rosbank.cache.models.entity.OrderEntity;

@Component
@AllArgsConstructor
public class OrderMapping {
    private CounterpartyMapping counterpartyMapping;

    public OrderDto toMap(OrderEntity orderEntity, CounterpartyEntity counterpartyEntity) {
        var result = new OrderDto();
        result.setOrderId(orderEntity.getOrderId());
        result.setAmount(orderEntity.getAmount());
        result.setDateTime(orderEntity.getDateTime());
        result.setCounterparty(counterpartyMapping.toMap(counterpartyEntity));
        return result;
    }
}
