package com.gaxeris.templates.security.services;

import com.gaxeris.templates.security.dtos.GroupDto;
import com.gaxeris.templates.security.mappers.GroupMapper;
import com.gaxeris.templates.security.models.Group;
import com.gaxeris.templates.security.models.Namespace;
import com.gaxeris.templates.security.repositories.GroupRepository;
import com.gaxeris.templates.security.repositories.NamespaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    //region Dependencies
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    private final NamespaceRepository namespaceRepository;
    //endregion


    public GroupDto createGroupInNamespace(UUID namespaceId, GroupDto dto) {
        final Namespace namespace = namespaceRepository.findById(namespaceId).orElseThrow();

        Group group = groupMapper.toModel(dto);
        group.setNamespace(namespace);

        group = groupRepository.save(group);
        return groupMapper.toDto(group);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Group createDefaultGroup(Namespace namespace) {
        Group defaultGroup = new Group(
            namespace.getTitle() + "default_group",
                null,
                namespace
        );

        return groupRepository.save(defaultGroup);
    }

    public GroupDto getGroup(UUID id) {
        Group group = groupRepository.findById(id).orElseThrow();
        return groupMapper.toDto(group);
    }

    public Page<GroupDto> getAllGroupsByNamespace(UUID namespaceId, Pageable pageable) {
        final Namespace namespace = namespaceRepository.findById(namespaceId).orElseThrow();

        Page<Group> groups = groupRepository.findAllByNamespace(namespace, pageable);
        return groups.map(groupMapper::toDto);
    }

    public GroupDto updateGroup(UUID id, GroupDto dto) {
        Group group = groupRepository.findById(id).orElseThrow();

        groupMapper.updateModel(dto, group);
        group = groupRepository.save(group);
        return groupMapper.toDto(group);
    }

    public void deleteGroup(UUID id) {
        groupRepository.deleteById(id);
    }

}
