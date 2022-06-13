package io.moove.uberdatacomparator.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataSourceType {
    PRIMARY("primary"), SECONDARY("secondary");

    private final String name;
}
