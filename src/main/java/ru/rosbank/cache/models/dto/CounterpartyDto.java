package ru.rosbank.cache.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CounterpartyDto {

    @JsonProperty("counterpartyId")
    private Long counterpartyId;

    @JsonProperty("uniqueNumber")
    private int uniqueNumber;

    @JsonProperty("country")
    private String name;

}
