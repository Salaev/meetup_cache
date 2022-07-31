package ru.rosbank.cache.client;

import org.springframework.stereotype.Component;
import ru.rosbank.cache.models.entity.OrderEntity;

import java.util.List;

@Component
public interface OrderApi {

    List<OrderEntity> getOrders();
}
