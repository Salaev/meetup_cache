package ru.rosbank.cache.service;

import ru.rosbank.cache.models.dto.MethodDto;
import ru.rosbank.cache.models.dto.OrderDto;

import java.util.List;

public interface OrderServiceApi {

    List<OrderDto> getOrdersByFilter(MethodDto method, String requestId);
}
