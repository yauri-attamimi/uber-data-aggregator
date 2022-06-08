package io.moove.uberdatacomparator.uberapi.service;

import io.moove.uberdatacomparator.datapipeline.entity.DriverPaymentHistory;
import io.moove.uberdatacomparator.moovebackend.repository.MooveDriverRepository;
import io.moove.uberdatacomparator.service.IUberDriverService;
import io.moove.uberdatacomparator.uberapi.dto.PaymentRecord;
import io.moove.uberdatacomparator.uberapi.dto.UberPaymentResponse;
import io.moove.uberdatacomparator.uberapi.dto.UberTripResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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

    public List<DriverPaymentHistory> collectDriverPaymentRecords(String drn, String token, int offset, int limit) {
        var driversFound = mooveDriverRepository.findByDrnOrderByUpdatedDateDesc(drn);
        var selectedDriver = driversFound.size() > 0 ? driversFound.get(0) : null;

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
                    if (selectedDriver != null) {
                        operationResult.add(
                            DriverPaymentHistory.builder()
                                .id(rec.getPaymentId())
                                .drn(selectedDriver.getDrn())
                                .uberDriverId(rec.getDriverId())
                                .uberDriverUuid(selectedDriver.getUberId())
                                .uberPartnerId(rec.getPartnerId())
                                .name(selectedDriver.getName())
                                .email(selectedDriver.getEmail())
                                .phoneNo(selectedDriver.getPhone())
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
                        );
                    }
                }
            }
            return uberDriverService.batchUberPaymentInsert(operationResult);
        }).block();
    }

    public Mono<UberTripResponse> fetchDriverTrips(String drn, String token, int offset, int limit) {
        limit = (limit == 0 ? 50 : limit);
        var response = this.webClient.get()
                .uri("/partners/trips?offset=" + offset + "&limit=" + limit)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.set("Authorization", "Bearer " + token);
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                })
                .retrieve().bodyToMono(UberTripResponse.class).log();
        return response;
    }
}
