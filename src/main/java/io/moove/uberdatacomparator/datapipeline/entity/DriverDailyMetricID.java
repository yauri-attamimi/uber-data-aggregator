package io.moove.uberdatacomparator.datapipeline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DriverDailyMetricID implements Serializable {
    private LocalDate date;
    private String drn;
}
