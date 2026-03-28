package com.gaxeris.templates.security.controllers;

import com.gaxeris.templates.security.dtos.GroupDto;
import com.gaxeris.templates.security.services.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/namespaces/{namespaceId}/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/")
    public ResponseEntity<GroupDto> createGroup(
            @PathVariable UUID namespaceId,
            @RequestBody GroupDto dto
    ) {
        return ResponseEntity.ok(groupService.createGroupInNamespace(namespaceId, dto));
    }

    @GetMapping("/")
    public ResponseEntity<Page<GroupDto>> getAllGroups(
            @PathVariable UUID namespaceId,
            @PageableDefault(sort = "title") Pageable pageable
    ) {
        return ResponseEntity.ok(groupService.getAllGroupsByNamespace(namespaceId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> readGroup(
            @PathVariable UUID namespaceId,
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(groupService.getGroup(id));
    }

    @SneakyThrows
    @PatchMapping("/{id}")
    public ResponseEntity<GroupDto> patchGroup(
            @PathVariable UUID namespaceId,
            @PathVariable UUID id,
            @RequestBody GroupDto dto
    ) {
        return ResponseEntity.ok(groupService.updateGroup(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(
            @PathVariable UUID namespaceId,
            @PathVariable UUID id
    ) {
        groupService.deleteGroup(id);

        return ResponseEntity.ok().build();
    }


}
