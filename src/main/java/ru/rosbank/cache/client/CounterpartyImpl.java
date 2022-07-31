package ru.rosbank.cache.client;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.rosbank.cache.models.entity.CounterpartyEntity;

@Component
@CacheConfig
public class CounterpartyImpl implements CounterpartyApi {

    @Override
    public CounterpartyEntity getCounterpartyByNumber(int uniqueNumber) {
        return getCounterparty(uniqueNumber);
    }

    @Override
    @Cacheable(cacheNames = {"counterparty"}, key = "{#uniqueNumber}", cacheManager = "defaultCacheManager")
    public CounterpartyEntity getCounterpartyByNumberWithCaching(int uniqueNumber, String requestId) {
        return getCounterparty(uniqueNumber);
    }

/*
    @Override
    @Cacheable(cacheNames = {"counterparty"}, key = "{#uniqueNumber}", cacheResolver = "multipleCacheResolver")
    public CounterpartyEntity getCounterpartyByNumberWithCaching(int uniqueNumber, String requestId) {
        return getCounterparty(uniqueNumber);
    }
*/

    private CounterpartyEntity getCounterparty(int uniqueNumber) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CounterpartyEntity(RandomUtils.nextLong(0, 1000000), uniqueNumber, "name");
    }
}
