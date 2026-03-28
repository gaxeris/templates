package com.gaxeris.templates.security.services;

import com.gaxeris.templates.security.dtos.GroupDto;
import com.gaxeris.templates.security.models.Group;
import com.gaxeris.templates.security.models.Namespace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface GroupService {

    GroupDto createGroupInNamespace(UUID namespaceId, GroupDto dto);

    Group createDefaultGroup(Namespace namespace);

    GroupDto getGroup(UUID id);

    Page<GroupDto> getAllGroupsByNamespace(UUID namespaceId, Pageable pageable);

    GroupDto updateGroup(UUID id, GroupDto dto);

    void deleteGroup(UUID id);

}
