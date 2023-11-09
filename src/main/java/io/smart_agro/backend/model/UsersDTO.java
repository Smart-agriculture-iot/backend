package io.smart_agro.backend.model;

import io.smart_agro.backend.domain.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsersDTO {

    private Long userId;

    @NotNull
    @Size(max = 255)
    private String fullname;

    @NotNull
    @Size(max = 255)
    private String username;

    
    @Size(max = 255)
    private String password;
    
    @Size(max = 255)
    private String province;
   
    private String cooperative;

   
    private Long createdBy;

    @Size(max = 255)
    private String status;

    private Role roleId;

}
