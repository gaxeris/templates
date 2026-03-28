package com.gaxeris.templates.security.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Optional;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NamespaceDto {

    @JsonProperty("id")
    Optional<UUID> id;

    @JsonProperty("title")
    Optional<String> title;

    @JsonProperty("description")
    Optional<String> description;

}
