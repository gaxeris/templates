package com.gaxeris.templates.security.mappers;

import com.gaxeris.templates.security.dtos.NamespaceDto;
import com.gaxeris.templates.security.models.Namespace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NamespaceMapperTest {

    @Autowired
    private NamespaceMapper namespaceMapper;

    private final String TITLE = "title";
    private final String DESCRIPTION = "description";

    private final String UPDATED_TITLE = "updatedTitle";
    private final String UPDATED_DESCRIPTION = "updatedDescription";

    @Test
    void updateModel_ignoreId() {
        // Arrange
        final UUID originalId = UUID.randomUUID();
        Namespace namespace = new Namespace(
                originalId,
                TITLE,
                DESCRIPTION
        );
        final Namespace updatedNamespace = new Namespace(
                originalId,
                UPDATED_TITLE,
                UPDATED_DESCRIPTION
        );

        final UUID ignoredId = UUID.randomUUID();
        NamespaceDto namespaceDto = new NamespaceDto(
                Optional.of(ignoredId),
                Optional.of(UPDATED_TITLE),
                Optional.of(UPDATED_DESCRIPTION)
        );


        // Act
        namespaceMapper.updateModel(namespaceDto, namespace);

        // Assert
        assertNotEquals(ignoredId, namespace.getId());

        assertEquals(updatedNamespace.getId(), namespace.getId());
        assertEquals(updatedNamespace.getTitle(), namespace.getTitle());
        assertEquals(updatedNamespace.getDescription(), namespace.getDescription());
    }

    @Test
    void updateModel_changeOnlyProvidedFieldAndKeepOmittedIntact_whenDtoIsPartial() {
        // Arrange
        final UUID originalId = UUID.randomUUID();
        Namespace namespace = new Namespace(
                originalId,
                TITLE,
                DESCRIPTION
        );
        final Namespace updatedNamespace = new Namespace(
                originalId,
                UPDATED_TITLE,
                DESCRIPTION
        );

        final NamespaceDto namespaceDto = new NamespaceDto(
                null,
                Optional.of(UPDATED_TITLE),
                null
        );

        // Act
        namespaceMapper.updateModel(namespaceDto, namespace);

        // Assert
        assertEquals(updatedNamespace.getId(), namespace.getId());
        assertEquals(updatedNamespace.getTitle(), namespace.getTitle());
        assertEquals(updatedNamespace.getDescription(), namespace.getDescription());
    }

    @Test
    void updateModel_setFieldToNull_whenDtoHasExplicitlyNullValue() {
        // Arrange
        final UUID originalId = UUID.randomUUID();
        Namespace namespace = new Namespace(
                originalId,
                TITLE,
                DESCRIPTION
        );
        final Namespace updatedNamespace = new Namespace(
                originalId,
                TITLE,
                null
        );

        final NamespaceDto namespaceDto = new NamespaceDto(
                null,
                null,
                Optional.empty()
        );


        // Act
        namespaceMapper.updateModel(namespaceDto, namespace);

        // Assert
        assertEquals(updatedNamespace.getId(), namespace.getId());
        assertEquals(updatedNamespace.getTitle(), namespace.getTitle());
        assertEquals(updatedNamespace.getDescription(), namespace.getDescription());
    }
}