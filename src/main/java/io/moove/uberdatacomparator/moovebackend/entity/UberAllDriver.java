package io.moove.uberdatacomparator.moovebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver_uberalldriver")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UberAllDriver {

    @Id
    private Integer id;
    @Column(name = "bank_deposit")
    private BigDecimal bankDeposit;
    @Column(name = "uber_driver_id")
    private String uberDriverId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private BigDecimal total;
    private Integer trips;
    private BigDecimal fare;
    @Column(name = "wait_time")
    private Double waitTime;
    private BigDecimal toll;
    private BigDecimal surge;
    private BigDecimal collection;
    @Column(name = "uber_Fee")
    private BigDecimal uberFee;
    private BigDecimal payouts;
    @Column(name = "other_promotion")
    private BigDecimal otherPromotion;
    private BigDecimal tip;
    @Column(name = "airport_charge")
    private BigDecimal airportCharge;
    @Column(name = "uber_fee_collection")
    private BigDecimal uberFeeCollection;
    @Column(name = "adjusted_fare")
    private BigDecimal adjustedFare;
    @Column(name = "uber_fee_collection_return")
    private BigDecimal uberFeeCollectionReturn;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private LocalDate date;
    @Column(name = "driver_id")
    private Integer driverId;
    @Column(name = "country_id")
    private Integer countryId;
    @Column(name = "city_id")
    private BigInteger cityId;
}
