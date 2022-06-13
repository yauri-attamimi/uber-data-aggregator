package io.moove.uberdatacomparator.uberapi.dto.payment;

import lombok.*;

import java.util.List;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UberPaymentResponse {

    private int offset;
    private int limit;
    private long count;
    private List<PaymentRecord> payments;
}
