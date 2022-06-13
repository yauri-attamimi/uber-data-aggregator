package io.moove.uberdatacomparator.uberapi.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RiderFeeComponent {

    @JsonProperty("split_fare")
    private double splitFare;
}
