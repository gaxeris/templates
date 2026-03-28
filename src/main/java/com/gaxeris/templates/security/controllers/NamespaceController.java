package com.gaxeris.templates.security.controllers;

import com.gaxeris.templates.security.dtos.NamespaceDto;
import com.gaxeris.templates.security.models.Namespace;
import com.gaxeris.templates.security.services.NamespaceService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/namespaces")
@RequiredArgsConstructor
public class NamespaceController {

    //region Dependencies
    private final NamespaceService namespaceService;
    //endregion

    @PostMapping("/")
    public Namespace createNamespace(
            @RequestBody NamespaceDto dto
    ) {
        return namespaceService.createNamespace(dto);
    }

    @GetMapping("/")
    public Page<Namespace> getAllNamespaces(
            @PageableDefault(sort = "title")
            Pageable pageable
    ) {
        return namespaceService.getAllNamespaces(pageable);
    }

    @GetMapping("/{id}")
    public Namespace readNamespace(
            @PathVariable UUID id
    ) {
        return namespaceService.getNamespace(id);
    }

    @SneakyThrows
    @PatchMapping("/{id}")
    public Namespace patchNamespace(
            @PathVariable UUID id,
            @RequestBody NamespaceDto dto
    ) {
        return namespaceService.updateNamespace(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteNamespace(
            @PathVariable UUID id
    ) {
        namespaceService.deleteNamespace(id);
    }


}
