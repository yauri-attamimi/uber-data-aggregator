package io.moove.uberdatacomparator.moovebackend.repository;

import io.moove.uberdatacomparator.moovebackend.entity.UberCredential;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UberCredentialRepository extends JpaRepository<UberCredential, Integer> {

    @Query("SELECT d.drn FROM UberCredential d")
    List<String> fetchAllDrns();

    Optional<UberCredential> findByDrn(String drn);
}
