package io.moove.uberdatacomparator.controller;

import com.google.api.services.drive.model.File;
import io.moove.uberdatacomparator.UberDataComparatorApplication;
import io.moove.uberdatacomparator.datapipeline.entity.DriverPaymentHistory;
import io.moove.uberdatacomparator.datapipeline.entity.DriverTripHistory;
import io.moove.uberdatacomparator.gdrive.service.CSVReaderService;
import io.moove.uberdatacomparator.service.IUberDriverService;
import io.moove.uberdatacomparator.uberapi.service.UberClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/uber_drivers")
@Slf4j
public class UberDriverController {

    private final IUberDriverService uberCredentialService;
    private final UberClientService uberClientService;
    private final CSVReaderService csvReaderService;

    @GetMapping("/drns")
    public List<String> fetchAllDriverDRNs() {
        UberDataComparatorApplication.drnWithCredentials = uberCredentialService.fetchAllDrns();
        return UberDataComparatorApplication.drnWithCredentials;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<DriverPaymentHistory>> fetchUberPayments(
            @RequestHeader("Authorization") String accessToken,
            @RequestParam(required = false) Optional<String> start,
            @RequestParam(required = false) Optional<String> max) throws NumberFormatException {
        int offset = start.map(Integer::parseInt).orElse(0);
        int limit = max.map(Integer::parseInt).orElse(50);
        return ResponseEntity.ok(uberClientService.collectDriverPaymentRecords(accessToken, offset, limit));
    }

    @GetMapping("/trips")
    public ResponseEntity<List<DriverTripHistory>> fetchUberTrips(
            @RequestHeader("Authorization") String accessToken,
            @RequestParam(required = false) Optional<String> start,
            @RequestParam(required = false) Optional<String> max) throws NumberFormatException {
        int offset = start.map(Integer::parseInt).orElse(0);
        int limit = max.map(Integer::parseInt).orElse(50);
        return ResponseEntity.ok(uberClientService.collectDriverTripRecords(accessToken, offset, limit));
    }

    @GetMapping("/gdrive_payments")
    public ResponseEntity<List<File>> fetchPaymentDashboardFiles(
            @RequestParam(required = false) Optional<String> max) throws NumberFormatException {
        int limit = max.map(Integer::parseInt).orElse(10);
        try {
            return ResponseEntity.ok(
                    csvReaderService.listCSVFiles("Payment", "text/csv", 10));
        } catch (IOException | GeneralSecurityException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
