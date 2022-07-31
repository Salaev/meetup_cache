package ru.rosbank.cache.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterpartyEntity implements Serializable {

    private Long counterpartyId;
    private int uniqueNumber;
    private String name;

}
