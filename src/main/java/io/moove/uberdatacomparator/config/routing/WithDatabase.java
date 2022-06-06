package io.moove.uberdatacomparator.config.routing;

import io.moove.uberdatacomparator.config.DataSourceType;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Transactional
public @interface WithDatabase {

    DataSourceType value() default DataSourceType.PRIMARY;
}
