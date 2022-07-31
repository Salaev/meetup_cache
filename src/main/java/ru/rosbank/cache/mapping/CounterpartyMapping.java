package ru.rosbank.cache.mapping;

import org.springframework.stereotype.Component;
import ru.rosbank.cache.models.dto.CounterpartyDto;
import ru.rosbank.cache.models.entity.CounterpartyEntity;

@Component
public class CounterpartyMapping {
    public CounterpartyDto toMap(CounterpartyEntity counterpartyEntity) {
        return new CounterpartyDto(
                counterpartyEntity.getCounterpartyId(),
                counterpartyEntity.getUniqueNumber(),
                counterpartyEntity.getName()
        );
    }
}
