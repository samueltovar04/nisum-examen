package com.nisum.examen.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;
    private String token;
    @JsonProperty("is_active")
    private Boolean isActive;
    private List<PhoneDto> phones;
}
