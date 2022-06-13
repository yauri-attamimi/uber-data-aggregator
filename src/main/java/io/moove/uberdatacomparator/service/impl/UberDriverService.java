package io.moove.uberdatacomparator.service.impl;

import io.moove.uberdatacomparator.config.db.DataSourceType;
import io.moove.uberdatacomparator.config.routing.WithDatabase;
import io.moove.uberdatacomparator.datapipeline.entity.DriverPaymentHistory;
import io.moove.uberdatacomparator.datapipeline.entity.DriverTripHistory;
import io.moove.uberdatacomparator.datapipeline.repository.DriverDailyMetricRepository;
import io.moove.uberdatacomparator.datapipeline.repository.DriverPaymentHistoryRepository;
import io.moove.uberdatacomparator.datapipeline.repository.DriverTripHistoryRepository;
import io.moove.uberdatacomparator.moovebackend.repository.UberCredentialRepository;
import io.moove.uberdatacomparator.service.IUberDriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UberDriverService implements IUberDriverService {

    private final DriverDailyMetricRepository metricRepository;
    private final UberCredentialRepository uberCredentialRepository;
    private final DriverPaymentHistoryRepository driverPaymentHistoryRepository;
    private final DriverTripHistoryRepository driverTripHistoryRepository;

    @Override
    @WithDatabase(DataSourceType.PRIMARY)
    public List<String> fetchAllDrns() {
        return uberCredentialRepository.fetchAllDrns();
    }

    @Override
    @WithDatabase(DataSourceType.SECONDARY)
    public Set<String> filterByDrns(List<String> drns) {
        return metricRepository.findByDrnIn(drns);
    }

    @Override
    @WithDatabase(DataSourceType.SECONDARY)
    public List<DriverPaymentHistory> batchUberPaymentInsert(List<DriverPaymentHistory> paymentHistories) {
        return driverPaymentHistoryRepository.saveAll(paymentHistories);
    }

    @Override
    @WithDatabase(DataSourceType.SECONDARY)
    public List<DriverTripHistory> batchUberTripInsert(List<DriverTripHistory> tripHistories) {
        return driverTripHistoryRepository.saveAll(tripHistories);
    }
}
