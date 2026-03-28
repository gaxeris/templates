package com.gaxeris.templates.security.mappers;

import com.gaxeris.templates.common.mappers.OptionalFieldMapper;
import com.gaxeris.templates.security.dtos.GroupDto;
import com.gaxeris.templates.security.models.Group;
import com.gaxeris.templates.security.models.Namespace;
import com.gaxeris.templates.security.repositories.NamespaceRepository;
import com.gaxeris.templates.security.services.NamespaceService;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {OptionalFieldMapper.class}
)
public interface GroupMapper {


    @Mappings({
            @Mapping(target= "id", ignore = true),
            @Mapping(target = "namespace", ignore = true),
    })
    Group toModel(GroupDto dto);


    GroupDto toDto(Group model);

    @InheritConfiguration
    void updateModel(GroupDto dto, @MappingTarget Group model);
    
}
