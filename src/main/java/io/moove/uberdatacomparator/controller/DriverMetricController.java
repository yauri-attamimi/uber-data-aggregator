package io.moove.uberdatacomparator.controller;

import io.moove.uberdatacomparator.UberDataComparatorApplication;
import io.moove.uberdatacomparator.service.IUberDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class DriverMetricController {

    private final IUberDriverService service;

    @GetMapping("/drnWithCredentials")
    public Set<String> fetchDrnWithCredentials() {
        return service.filterByDrns(UberDataComparatorApplication.drnWithCredentials);
    }
}
