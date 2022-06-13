package io.moove.uberdatacomparator.config.db;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author yauritux (yauri.attamimi@moove.io)
 */
@Getter
@RequiredArgsConstructor
public enum DataSourceType {
    PRIMARY("primary"), SECONDARY("secondary");

    private final String name;
}
