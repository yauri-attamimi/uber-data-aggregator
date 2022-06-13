package io.moove.uberdatacomparator.uberapi.service;

import io.moove.uberdatacomparator.datapipeline.entity.DriverPaymentHistory;
import io.moove.uberdatacomparator.datapipeline.entity.DriverTripHistory;
import io.moove.uberdatacomparator.moovebackend.repository.MooveDriverRepository;
import io.moove.uberdatacomparator.service.IUberDriverService;
import io.moove.uberdatacomparator.uberapi.dto.payment.PaymentRecord;
import io.moove.uberdatacomparator.uberapi.dto.payment.UberPaymentResponse;
import io.moove.uberdatacomparator.uberapi.dto.trip.StatusChanges;
import io.moove.uberdatacomparator.uberapi.dto.trip.TripRecord;
import io.moove.uberdatacomparator.uberapi.dto.trip.UberTripResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@Service
@Slf4j
public class UberClientService {

    private final WebClient webClient;
    private final MooveDriverRepository mooveDriverRepository;
    private final IUberDriverService uberDriverService;

    public UberClientService(MooveDriverRepository mooveDriverRepository, IUberDriverService uberDriverService) {
        this.webClient = WebClient.builder().baseUrl("https://api.uber.com/v1").build();
        this.mooveDriverRepository = mooveDriverRepository;
        this.uberDriverService = uberDriverService;
    }

    public List<DriverPaymentHistory> collectDriverPaymentRecords(String token, int offset, int limit) {
        var selectedDriver = mooveDriverRepository.searchByAccessToken(token);

        limit = (limit == 0 ? 50 : limit);
        var response = this.webClient.get()
                .uri("/partners/payments?offset=" + offset + "&limit=" + limit)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.set("Authorization", "Bearer " + token);
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                })
                .retrieve().bodyToMono(UberPaymentResponse.class).log();

        return response.map(r -> {
            var operationResult = new ArrayList<DriverPaymentHistory>();
            if (r.getPayments() != null && r.getPayments().size() > 0) {
                for (PaymentRecord rec : r.getPayments()) {
                    selectedDriver.ifPresent(mooveDriver -> operationResult.add(
                            DriverPaymentHistory.builder()
                                    .id(rec.getPaymentId())
                                    .drn(mooveDriver.getDrn())
                                    .uberDriverId(rec.getDriverId())
                                    .uberDriverUuid(mooveDriver.getUberId())
                                    .uberPartnerId(rec.getPartnerId())
                                    .name(mooveDriver.getName())
                                    .email(mooveDriver.getEmail())
                                    .phoneNo(mooveDriver.getPhone())
                                    .eventTime(rec.getEventTime())
                                    .eventTimeTs(LocalDateTime.ofInstant(Instant.ofEpochMilli(rec.getEventTime()), TimeZone.getDefault().toZoneId()))
                                    .category(rec.getCategory())
                                    .amount(BigDecimal.valueOf(rec.getAmount()))
                                    .cashCollected(BigDecimal.valueOf(rec.getCashCollected()))
                                    .serviceFee(BigDecimal.valueOf(rec.getBreakdown() != null ? rec.getBreakdown().getServiceFee() : 0))
                                    .otherFee(BigDecimal.valueOf(rec.getBreakdown() != null ? rec.getBreakdown().getOther() : 0))
                                    .splitFare(BigDecimal.valueOf(rec.getRiderFees() != null ? rec.getRiderFees().getSplitFare() : 0))
                                    .uberTripId(rec.getTripId())
                                    .currencyCode(rec.getCurrencyCode())
                                    .build()
                    ));
                }
            }
            return uberDriverService.batchUberPaymentInsert(operationResult);
        }).block();
    }

    public List<DriverTripHistory> collectDriverTripRecords(String token, int offset, int limit) {
        var selectedDriver = mooveDriverRepository.searchByAccessToken(token);

        limit = (limit == 0 ? 50 : limit);

        var response = this.webClient.get()
                .uri("/partners/trips?offset=" + offset + "&limit=" + limit)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.set("Authorization", "Bearer " + token);
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                })
                .retrieve().bodyToMono(UberTripResponse.class).log();

        return response.map(r -> {
            var operationResult = new ArrayList<DriverTripHistory>();
            if (r.getTrips() != null && r.getTrips().size() > 0) {
                for (TripRecord rec : r.getTrips()) {
                    for (StatusChanges stat : rec.getStatusChanges()) {
                        selectedDriver.ifPresent(mooveDriver -> operationResult.add(
                                DriverTripHistory.builder()
                                        .id(rec.getTripId())
                                        .drn(mooveDriver.getDrn())
                                        .vehicleId(rec.getVehicleId())
                                        .uberDriverId(rec.getDriverId())
                                        .uberDriverUuid(mooveDriver.getUberId())
                                        .name(mooveDriver.getName())
                                        .email(mooveDriver.getEmail())
                                        .phoneNo(mooveDriver.getPhone())
                                        .pickupUnixTs(rec.getPickup() != null ? rec.getPickup().getTimestamp() : 0)
                                        .pickupTimestamp(rec.getPickup() != null ? LocalDateTime.ofInstant(
                                                Instant.ofEpochMilli(
                                                        rec.getPickup().getTimestamp()), TimeZone.getDefault().toZoneId()) : null)
                                        .dropoffUnixTs(rec.getDropoff() != null ? rec.getDropoff().getTimestamp() : 0)
                                        .dropoffTimestamp(rec.getDropoff() != null ? LocalDateTime.ofInstant(
                                                Instant.ofEpochMilli(
                                                        rec.getDropoff().getTimestamp()), TimeZone.getDefault().toZoneId()) : null)
                                        .fare(BigDecimal.valueOf(rec.getFare()))
                                        .currencyCode(rec.getCurrencyCode())
                                        .distance(rec.getDistance())
                                        .duration(rec.getDuration())
                                        .surgeMultiplier(rec.getSurgeMultiplier())
                                        .status(stat.getStatus())
                                        .statusTimestamp(stat.getTimestamp())
                                        .build()
                        ));
                    }
                }
            }
            return uberDriverService.batchUberTripInsert(operationResult);
        }).block();
    }
}
