package io.moove.uberdatacomparator.datapipeline.repository;

import io.moove.uberdatacomparator.datapipeline.entity.DriverPaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverPaymentHistoryRepository extends JpaRepository<DriverPaymentHistory, String> {
}
