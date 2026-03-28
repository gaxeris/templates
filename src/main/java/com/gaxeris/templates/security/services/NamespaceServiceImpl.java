package com.gaxeris.templates.security.services;

import com.gaxeris.templates.security.dtos.NamespaceDto;
import com.gaxeris.templates.security.mappers.NamespaceMapper;
import com.gaxeris.templates.security.models.Namespace;
import com.gaxeris.templates.security.repositories.NamespaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NamespaceServiceImpl implements NamespaceService {

    //region Dependencies
    private final NamespaceRepository namespaceRepository;
    private final NamespaceMapper namespaceMapper;

    private final GroupService groupService;
    //endregion

    @Transactional
    public Namespace createNamespace(NamespaceDto dto) {
        Namespace namespace = namespaceMapper.toModel(dto);

        namespace = namespaceRepository.save(namespace);
        groupService.createDefaultGroup(namespace);

        return namespace;
    }

    public Page<Namespace> getAllNamespaces(
            Pageable pageable
    ) {
        return namespaceRepository.findAll(pageable);
    }

    public Namespace getNamespace(UUID id) {
        return namespaceRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Namespace updateNamespace(UUID id, NamespaceDto dto) {
        Namespace namespace = getNamespace(id);

        namespaceMapper.updateModel(dto, namespace);
        return namespaceRepository.save(namespace);
    }

    public void deleteNamespace(UUID id) {
        namespaceRepository.deleteById(id);
    }

}
