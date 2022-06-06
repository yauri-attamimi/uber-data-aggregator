package io.moove.uberdatacomparator;

import io.moove.uberdatacomparator.service.IUberDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UberDataComparatorApplication {

    public static List<String> drnWithCredentials = new ArrayList<>();

    @Autowired
    private IUberDriverService service;

    public static void main(String[] args) {
        SpringApplication.run(UberDataComparatorApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        drnWithCredentials = service.fetchAllDrns();
        System.out.println("List of DRNs with Uber Credentials=" + service.filterByDrns(drnWithCredentials));
    }

}
