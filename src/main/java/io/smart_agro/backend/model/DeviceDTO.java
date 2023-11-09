package io.smart_agro.backend.model;

import io.smart_agro.backend.domain.Cooperative;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeviceDTO {

    private Long deviceId;

    @NotNull
    @Size(max = 255)
    private String battery;

    @NotNull
    @Size(max = 255)
    private String longtitude;

    @NotNull
    @Size(max = 255)
    private String latitude;

    @NotNull
    @Size(max = 255)
    private String status;

 
    private Cooperative coId;

}
