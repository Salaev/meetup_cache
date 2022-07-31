package ru.rosbank.cache.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.rosbank.cache.models.dto.MethodDto;
import ru.rosbank.cache.models.dto.OrderDto;
import ru.rosbank.cache.service.OrderServiceApi;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    ApplicationContext context;

    private final OrderServiceApi service;

    @GetMapping("/orders/{method}")
    public List<OrderDto> getClients(@PathVariable("method") MethodDto method) {
        try {
            String requestId = UUID.randomUUID().toString();
            var result = service.getOrdersByFilter(method, requestId);
            return result;
        } catch (Exception ex) {
            log.error("Exception in controller:", ex);
            throw ex;
        }
    }
}
