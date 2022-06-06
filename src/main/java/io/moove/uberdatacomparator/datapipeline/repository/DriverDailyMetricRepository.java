package io.moove.uberdatacomparator.datapipeline.repository;

import io.moove.uberdatacomparator.datapipeline.entity.DriverDailyMetric;
import io.moove.uberdatacomparator.datapipeline.entity.DriverDailyMetricID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface DriverDailyMetricRepository extends JpaRepository<DriverDailyMetric, DriverDailyMetricID> {

    @Query("SELECT dm.id.drn FROM DriverDailyMetric dm WHERE dm.id.drn IN (:drns)")
    Set<String> findByDrnIn(@Param("drns") List<String> drns);
}
