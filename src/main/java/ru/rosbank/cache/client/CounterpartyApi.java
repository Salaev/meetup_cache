package ru.rosbank.cache.client;

import ru.rosbank.cache.models.entity.CounterpartyEntity;

public interface CounterpartyApi {

    CounterpartyEntity getCounterpartyByNumber(int uniqueNumber);

    CounterpartyEntity getCounterpartyByNumberWithCaching(int uniqueNumber, String requestId);
}
