package io.moove.uberdatacomparator.uberapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RiderFeeComponent {

    @JsonProperty("split_fare")
    private double splitFare;
}
