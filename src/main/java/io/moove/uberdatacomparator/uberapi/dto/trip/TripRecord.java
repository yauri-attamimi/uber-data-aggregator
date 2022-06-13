package io.moove.uberdatacomparator.uberapi.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TripRecord {

    @JsonProperty("trip_id")
    private String tripId;
    @JsonProperty("vehicle_id")
    private String vehicleId;
    @JsonProperty("driver_id")
    private String driverId;
    private Pickup pickup;
    private Dropoff dropoff;
    private double fare;
    @JsonProperty("currency_code")
    private String currencyCode;
    private double distance;
    private long duration;
    @JsonProperty("surge_multiplier")
    private int surgeMultiplier;
    private String status;
    @JsonProperty("status_changes")
    private List<StatusChanges> statusChanges;
    @JsonProperty("start_city")
    private StartCity startCity;
}
