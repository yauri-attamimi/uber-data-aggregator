package io.moove.uberdatacomparator.uberapi.dto.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusChanges {

    private String status;
    private long timestamp;
}
