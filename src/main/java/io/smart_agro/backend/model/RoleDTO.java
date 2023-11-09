package io.smart_agro.backend.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleDTO {

    private Long roleId;

    @NotNull
    @Size(max = 255)
    private String roleName;

    @NotNull
    @Size(max = 255)
    private String status;

}
