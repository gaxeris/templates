package com.gaxeris.templates.security.services;

import com.gaxeris.templates.security.dtos.NamespaceDto;
import com.gaxeris.templates.security.models.Namespace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.UUID;

@Service
public interface NamespaceService {

    Namespace createNamespace(NamespaceDto dto);

    Namespace getNamespace(UUID id);

    Page<Namespace> getAllNamespaces(Pageable pageable);

    Namespace updateNamespace(UUID id, NamespaceDto dto);

    void deleteNamespace(UUID id);
}
