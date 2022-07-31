package ru.rosbank.cache.client;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;
import ru.rosbank.cache.models.entity.OrderEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderImpl implements OrderApi {
    private final List<OrderEntity> orders;

    public OrderImpl() {
        var result = new ArrayList<OrderEntity>();
        for (int i = 0; i <= 100; i++) {
            // каждый 10 uniqueNumber повторяется
            result.add(new OrderEntity(i, LocalDateTime.now(), BigDecimal.valueOf(RandomUtils.nextLong()), i % 10));
        }
        this.orders = result;
    }

    @Override
    public List<OrderEntity> getOrders() {
        return orders;
    }

}
