package io.moove.uberdatacomparator.service;

import io.moove.uberdatacomparator.datapipeline.entity.DriverPaymentHistory;
import io.moove.uberdatacomparator.datapipeline.entity.DriverTripHistory;

import java.util.List;
import java.util.Set;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
public interface IUberDriverService {

    List<String> fetchAllDrns();
    Set<String> filterByDrns(List<String> drns);
    List<DriverPaymentHistory> batchUberPaymentInsert(List<DriverPaymentHistory> paymentHistories);
    List<DriverTripHistory> batchUberTripInsert(List<DriverTripHistory> tripHistories);
}
