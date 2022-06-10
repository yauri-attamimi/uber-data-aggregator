package io.moove.uberdatacomparator.uberapi.dto.payment;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UberPaymentResponse {

    private int offset;
    private int limit;
    private long count;
    private List<PaymentRecord> payments;
}
