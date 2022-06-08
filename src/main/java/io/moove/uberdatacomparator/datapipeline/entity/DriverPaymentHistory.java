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

@Entity
@Table(name = "uber_driver_payment_histories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DriverPaymentHistory {

    @Id
    private String id;
    private String drn;
    @Column(name = "uber_driver_id")
    private String uberDriverId;
    @Column(name = "uber_driver_uuid")
    private String uberDriverUuid;
    @Column(name = "uber_partner_id")
    private String uberPartnerId;
    private String name;
    private String email;
    @Column(name = "phone_no")
    private String phoneNo;
    @Column(name = "event_time")
    private long eventTime;
    @Column(name = "event_time_ts")
    private LocalDateTime eventTimeTs;
    private String category;
    private BigDecimal amount;
    @Column(name = "cash_collected")
    private BigDecimal cashCollected;
    @Column(name = "service_fee")
    private BigDecimal serviceFee;
    @Column(name = "other_fee")
    private BigDecimal otherFee;
    @Column(name = "split_fare")
    private BigDecimal splitFare;
    @Column(name = "uber_trip_id")
    private String uberTripId;
    @Column(name = "currency_code")
    private String currencyCode;
}
