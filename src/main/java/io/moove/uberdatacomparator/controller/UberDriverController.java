package io.moove.uberdatacomparator.controller;

import io.moove.uberdatacomparator.UberDataComparatorApplication;
import io.moove.uberdatacomparator.service.IUberDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class UberDriverController {

    private final IUberDriverService uberCredentialService;

    @GetMapping("/drns")
    public List<String> fetchAllDriverDRNs() {
        UberDataComparatorApplication.drnWithCredentials = uberCredentialService.fetchAllDrns();
        return UberDataComparatorApplication.drnWithCredentials;
    }
}
