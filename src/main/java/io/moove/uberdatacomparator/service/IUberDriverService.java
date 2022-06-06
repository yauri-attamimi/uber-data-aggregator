package io.moove.uberdatacomparator.service;

import java.util.List;
import java.util.Set;

public interface IUberDriverService {

    List<String> fetchAllDrns();
    Set<String> filterByDrns(List<String> drns);
}
