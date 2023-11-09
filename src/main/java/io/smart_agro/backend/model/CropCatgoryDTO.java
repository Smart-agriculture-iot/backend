package io.smart_agro.backend.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CropCatgoryDTO {

    private Long categoryId;

    @NotNull
    @Size(max = 255)
    private String cropNames;

    @Size(max = 255)
    private String status;

    @NotNull
    private Long createdBy;

}
