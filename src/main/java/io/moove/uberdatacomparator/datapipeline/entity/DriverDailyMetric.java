package io.moove.uberdatacomparator.datapipeline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "driver_daily_metrics", uniqueConstraints = { @UniqueConstraint(columnNames = {"date", "drn"})})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DriverDailyMetric {

    @EmbeddedId
    private DriverDailyMetricID id;

    @Column(name = "net_earnings")
    private BigDecimal netEarnings;
    private BigInteger trips;
    @Column(name = "supply_hours")
    private String supplyHours;
    @Column(name = "km_driven")
    private double kmDriven;
    @Column(name = "acceptance_rate")
    private double acceptanceRate;
    @Column(name = "cancellation_rate")
    private double cancellationRate;
}
