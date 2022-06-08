package io.moove.uberdatacomparator.uberapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentComponent {

    @JsonProperty("service_fee")
    private double serviceFee;
    private double other;
    private double toll;
}
