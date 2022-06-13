package io.moove.uberdatacomparator.config.db;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataSourceType {
    PRIMARY("primary"), SECONDARY("secondary");

    private final String name;
}
