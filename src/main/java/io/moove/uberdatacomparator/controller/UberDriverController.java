package io.moove.uberdatacomparator.controller;

import io.moove.uberdatacomparator.UberDataComparatorApplication;
import io.moove.uberdatacomparator.datapipeline.entity.DriverPaymentHistory;
import io.moove.uberdatacomparator.service.IUberDriverService;
import io.moove.uberdatacomparator.uberapi.service.UberClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uber_drivers")
public class UberDriverController {

    private final IUberDriverService uberCredentialService;
    private final UberClientService uberClientService;

    @GetMapping("/drns")
    public List<String> fetchAllDriverDRNs() {
        UberDataComparatorApplication.drnWithCredentials = uberCredentialService.fetchAllDrns();
        return UberDataComparatorApplication.drnWithCredentials;
    }

    @GetMapping("/payments/{drn}")
    public ResponseEntity<List<DriverPaymentHistory>> fetchUberPayments(
            @PathVariable String drn,
            @RequestHeader("Authorization") String accessToken,
            @RequestParam(required = false) Optional<String> start,
            @RequestParam(required = false) Optional<String> max) throws NumberFormatException {
        int offset = 0;
        int limit = 50;
        if (start.isPresent()) {
            offset = Integer.parseInt(start.get());
        }
        if (max.isPresent()) {
            limit = Integer.parseInt(max.get());
        }
        return ResponseEntity.ok(uberClientService.collectDriverPaymentRecords(drn, accessToken, offset, limit));
    }
}
