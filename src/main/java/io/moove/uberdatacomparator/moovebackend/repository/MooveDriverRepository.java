package io.moove.uberdatacomparator.moovebackend.repository;

import io.moove.uberdatacomparator.moovebackend.entity.MooveDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MooveDriverRepository extends JpaRepository<MooveDriver, Integer> {

    List<MooveDriver> findByDrnOrderByUpdatedDateDesc(String drn);
}
