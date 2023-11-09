package io.smart_agro.backend.model;

import io.smart_agro.backend.domain.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SupervisorDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String fullname;

    @NotNull
    @Size(max = 255)
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    @Size(max = 255)
    private String province;

    @NotNull
    private Long createdBy;

    @NotNull
    @Size(max = 255)
    private String status;

    @NotNull
    private Role roleId;

    @NotNull
    @Size(max = 255)
    private String district;

    @NotNull
    @Size(max = 255)
    private String sector;

    @NotNull
    @Size(max = 255)
    private String cell;

}
