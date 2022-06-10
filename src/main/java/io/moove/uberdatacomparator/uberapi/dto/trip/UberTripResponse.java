package io.moove.uberdatacomparator.uberapi.dto.trip;

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
    private List<TripRecord> trips;
}
