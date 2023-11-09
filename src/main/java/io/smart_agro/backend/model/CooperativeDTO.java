package io.smart_agro.backend.model;

import io.smart_agro.backend.domain.CropCatgory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CooperativeDTO {

    private Long cooperativeId;

    @NotNull
    @Size(max = 255)
    private String coName;

    @NotNull
    @Size(max = 255)
    private String province;


    @NotNull
    @Size(max = 255)
    private String district;

    @NotNull
    @Size(max = 255)
    private String sector;

    @NotNull
    @Size(max = 255)
    private String cell;

    @Size(max = 255)
    private String status;

    
    private CropCatgory cropCategory;

}
