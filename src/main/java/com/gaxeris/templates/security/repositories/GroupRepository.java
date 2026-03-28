package com.gaxeris.templates.security.repositories;

import com.gaxeris.templates.security.dtos.GroupDto;
import com.gaxeris.templates.security.models.Group;
import com.gaxeris.templates.security.models.Namespace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface GroupRepository extends JpaRepository<Group, UUID> {

    Page<Group> findAllByNamespace(Namespace namespace, Pageable pageable);

}
