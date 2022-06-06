package io.moove.uberdatacomparator.service.impl;

import io.moove.uberdatacomparator.config.DataSourceType;
import io.moove.uberdatacomparator.config.routing.WithDatabase;
import io.moove.uberdatacomparator.datapipeline.repository.DriverDailyMetricRepository;
import io.moove.uberdatacomparator.moovebackend.repository.DriverUberCredentialRepository;
import io.moove.uberdatacomparator.service.IUberDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UberDriverService implements IUberDriverService {

    private final DriverDailyMetricRepository metricRepository;
    private final DriverUberCredentialRepository uberCredentialRepository;

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
}
