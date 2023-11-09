package io.smart_agro.backend.model;

import io.smart_agro.backend.domain.Device;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SensorDataDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String deviceId;

    @NotNull
    private String isRaining;

    @Size(max = 255)
    private String temperature;

    @Size(max = 255)
    private String soilmoisture;

    @Size(max = 255)
    private String humidity;

    @Size(max = 255)
    private String battery;

    @Size(max = 255)
    private String lat;
    
    @Size(max = 255)
    private String lng;

    private Boolean synced;

}
