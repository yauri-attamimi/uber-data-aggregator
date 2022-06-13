package io.moove.uberdatacomparator.datapipeline.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@Entity
@Table(name = "uber_driver_trip_histories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DriverTripHistory {

    @Id
    private String id;
    @Column(name = "vehicle_id")
    private String vehicleId;
    private String drn;
    @Column(name = "uber_driver_id")
    private String uberDriverId;
    @Column(name = "uber_driver_uuid")
    private String uberDriverUuid;
    private String name;
    private String email;
    @Column(name = "phone_no")
    private String phoneNo;
    @Column(name = "pickup_unix_ts")
    private Long pickupUnixTs;
    @Column(name = "pickup_timestamp")
    private LocalDateTime pickupTimestamp;
    @Column(name = "dropoff_unix_ts")
    private Long dropoffUnixTs;
    @Column(name = "dropoff_timestamp")
    private LocalDateTime dropoffTimestamp;
    private BigDecimal fare;
    @Column(name = "currency_code")
    private String currencyCode;
    private Double distance;
    private Long duration;
    @Column(name = "surge_multiplier")
    private Integer surgeMultiplier;
    private String status;
    @Column(name = "status_timestamp")
    private Long statusTimestamp;
}
