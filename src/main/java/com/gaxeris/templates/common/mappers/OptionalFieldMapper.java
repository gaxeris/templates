package com.gaxeris.templates.common.mappers;

import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface OptionalFieldMapper {

    default <T> Optional<T> wrap(T entity) {
        return Optional.of(entity);
    }

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}
