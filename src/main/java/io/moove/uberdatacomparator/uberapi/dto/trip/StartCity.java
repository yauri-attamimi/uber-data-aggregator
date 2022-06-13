package io.moove.uberdatacomparator.uberapi.dto.trip;

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
public class StartCity {

    @JsonProperty("display_name")
    private String displayName;
    private double latitude;
    private double longitude;
}
