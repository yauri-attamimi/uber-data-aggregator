package io.moove.uberdatacomparator.uberapi.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRecord {

    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("partner_id")
    private String partnerId;
    @JsonProperty("driver_id")
    private String driverId;
    private String category;
    private double amount;
    @JsonProperty("cash_collected")
    private double cashCollected;
    private PaymentComponent breakdown;
    @JsonProperty("rider_fees")
    private RiderFeeComponent riderFees;
    @JsonProperty("trip_id")
    private String tripId;
    @JsonProperty("currency_code")
    private String currencyCode;
    @JsonProperty("event_time")
    private long eventTime;
}
