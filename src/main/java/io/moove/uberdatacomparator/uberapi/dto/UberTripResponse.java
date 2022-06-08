package io.moove.uberdatacomparator.uberapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UberTripResponse {

    private int offset;
    private int limit;
    private long count;

    class Trip {
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
        private int duration;
        @JsonProperty("surge_multiplier")
        private int surgeMultiplier;
        private String status;
        @JsonProperty("status_changes")
        private List<StatusChanges> statusChanges;
        @JsonProperty("start_city")
        private StartCity startCity;
    }

    class Pickup {
        private long timestamp;
    }

    class Dropoff {
        private long timestamp;
    }

    class StatusChanges {
        private String status;
        private long timestamp;
    }

    class StartCity {
        @JsonProperty("display_name")
        private String displayName;
        private double latitude;
        private double longitude;
    }
}
