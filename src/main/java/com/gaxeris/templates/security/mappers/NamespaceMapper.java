package com.gaxeris.templates.security.mappers;

import com.gaxeris.templates.common.mappers.OptionalFieldMapper;
import com.gaxeris.templates.security.dtos.NamespaceDto;
import com.gaxeris.templates.security.models.Namespace;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {OptionalFieldMapper.class}
)
public interface NamespaceMapper {

    @Mapping(target = "id", ignore = true)
    Namespace toModel(NamespaceDto dto);

    NamespaceDto toDto(Namespace namespace);

    @InheritConfiguration
    void updateModel(NamespaceDto dto, @MappingTarget Namespace namespace);
}
