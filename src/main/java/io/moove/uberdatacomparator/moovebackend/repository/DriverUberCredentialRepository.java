package io.moove.uberdatacomparator.moovebackend.repository;

import io.moove.uberdatacomparator.moovebackend.entity.DriverUberCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverUberCredentialRepository extends JpaRepository<DriverUberCredential, Integer> {

    @Query("SELECT d.drn FROM DriverUberCredential d")
    List<String> fetchAllDrns();
}
