package ru.rosbank.cache.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rosbank.cache.client.CounterpartyApi;
import ru.rosbank.cache.client.OrderApi;
import ru.rosbank.cache.mapping.OrderMapping;
import ru.rosbank.cache.models.dto.MethodDto;
import ru.rosbank.cache.models.dto.OrderDto;
import ru.rosbank.cache.models.entity.OrderEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService implements OrderServiceApi {

    private CounterpartyApi counterpartyApi;
    private OrderApi orderApi;
    private OrderMapping orderMapping;

    @Override
    public List<OrderDto> getOrdersByFilter(MethodDto method, String requestId) {
        List<OrderDto> result;
        var orders = orderApi.getOrders();

        switch (method) {
            case WITHOUT_CACHE_SYNC:
                result = mappingWithoutCacheSync(orders);
                break;
            case WITHOUT_CACHE_ASYNC:
                result = mappingWithoutCacheAsync(orders);
                break;
            case WITH_MAP_SYNC:
                result = mappingWithMapSync(orders);
                break;
            case WITH_MAP_ASYNC:
                result = mappingWithMapAsync(orders);
                break;
            case WITH_CACHE_SYNC:
                result = mappingWithCacheSync(orders, requestId);
                break;
            case WITH_CACHE_ASYNC:
                result = mappingWithCacheAsync(orders, requestId);
                break;
            default:
                throw new IllegalArgumentException("method not found");
        }

        return result;
    }

    private List<OrderDto> mappingWithoutCacheSync(List<OrderEntity> values) {
        return values.stream()
                .map(it -> orderMapping.toMap(it, counterpartyApi.getCounterpartyByNumber(it.getCounterpartyUniqueNumber())))
                .collect(Collectors.toList());
    }

    private List<OrderDto> mappingWithoutCacheAsync(List<OrderEntity> values) {
        var executors = Executors.newFixedThreadPool(10);
        return values.stream()
                .map(it -> CompletableFuture.supplyAsync(() ->
                        orderMapping.toMap(it, counterpartyApi.getCounterpartyByNumber(it.getCounterpartyUniqueNumber())), executors))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private List<OrderDto> mappingWithMapSync(List<OrderEntity> values) {
        var uniqueNumber = values.stream()
                .map(OrderEntity::getCounterpartyUniqueNumber)
                .collect(Collectors.toSet());
        var counterparties = uniqueNumber.stream()
                .collect(Collectors.toMap(k -> k, v -> counterpartyApi.getCounterpartyByNumber(v)));
        return values.stream()
                .map(it -> orderMapping.toMap(it, counterparties.get(it.getCounterpartyUniqueNumber())))
                .collect(Collectors.toList());
    }

    private List<OrderDto> mappingWithMapAsync(List<OrderEntity> values) {
        var executors = Executors.newFixedThreadPool(10);
        var uniqueNumber = values.stream()
                .map(OrderEntity::getCounterpartyUniqueNumber)
                .collect(Collectors.toSet());
        var counterparties = uniqueNumber.stream()
                .collect(Collectors.toMap(k -> k, v -> CompletableFuture.supplyAsync(
                        () -> counterpartyApi.getCounterpartyByNumber(v), executors)))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().join()));
        return values.stream()
                .map(it -> orderMapping.toMap(it, counterparties.get(it.getCounterpartyUniqueNumber())))
                .collect(Collectors.toList());
    }

    private List<OrderDto> mappingWithCacheSync(List<OrderEntity> values, String requestId) {
        return values.stream()
                .map(it -> orderMapping.toMap(it, counterpartyApi.getCounterpartyByNumberWithCaching(it.getCounterpartyUniqueNumber(), requestId)))
                .collect(Collectors.toList());
    }

    private List<OrderDto> mappingWithCacheAsync(List<OrderEntity> values, String requestId) {
        var executors = Executors.newFixedThreadPool(10);
        return values.stream()
                .map(it -> CompletableFuture.supplyAsync(() ->
                        orderMapping.toMap(it, counterpartyApi.getCounterpartyByNumberWithCaching(it.getCounterpartyUniqueNumber(), requestId)), executors))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

}
