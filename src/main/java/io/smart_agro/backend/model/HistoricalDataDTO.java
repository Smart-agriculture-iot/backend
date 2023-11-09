package io.smart_agro.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HistoricalDataDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String temperature;

    @NotNull
    @JsonProperty("isRaining")
    private Boolean isRaining;

    @NotNull
    @Size(max = 255)
    private String humidity;

    @NotNull
    @Size(max = 255)
    private String soilmoisture;

}
