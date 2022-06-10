package io.moove.uberdatacomparator.datapipeline.repository;

import io.moove.uberdatacomparator.datapipeline.entity.DriverTripHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverTripHistoryRepository extends JpaRepository<DriverTripHistory, String> {
}
