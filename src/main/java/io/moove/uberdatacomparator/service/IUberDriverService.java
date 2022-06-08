package io.moove.uberdatacomparator.service;

import io.moove.uberdatacomparator.datapipeline.entity.DriverPaymentHistory;

import java.util.List;
import java.util.Set;

public interface IUberDriverService {

    List<String> fetchAllDrns();
    Set<String> filterByDrns(List<String> drns);
    List<DriverPaymentHistory> batchUberPaymentInsert(List<DriverPaymentHistory> paymentHistories);
}
