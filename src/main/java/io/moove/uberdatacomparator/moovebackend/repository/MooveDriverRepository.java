package io.moove.uberdatacomparator.moovebackend.repository;

import io.moove.uberdatacomparator.moovebackend.entity.MooveDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
public interface MooveDriverRepository extends JpaRepository<MooveDriver, Integer> {

    List<MooveDriver> findByDrnOrderByUpdatedDateDesc(String drn);

    @Query(value = "select a.* from driver_driver a inner join driver_ubercredential b on b.drn=a.drn " +
                    "where b.access_token= :accessToken and a.uber_status='Active'", nativeQuery = true)
    Optional<MooveDriver> searchByAccessToken(String accessToken);
}
