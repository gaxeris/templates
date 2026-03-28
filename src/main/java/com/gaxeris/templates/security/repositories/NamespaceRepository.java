package com.gaxeris.templates.security.repositories;

import com.gaxeris.templates.security.models.Namespace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface NamespaceRepository extends JpaRepository<Namespace, UUID> {
}
