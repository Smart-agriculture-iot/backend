package io.smart_agro.backend.model;

import io.smart_agro.backend.domain.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LogsDTO {

    private Long id;

    @NotNull
    private Users userId;

    @NotNull
    @Size(max = 255)
    private String activity;

}
