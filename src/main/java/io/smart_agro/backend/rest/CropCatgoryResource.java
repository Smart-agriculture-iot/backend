package io.smart_agro.backend.rest;

import io.smart_agro.backend.model.CropCatgoryDTO;
import io.smart_agro.backend.service.CropCatgoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/cropCatgories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CropCatgoryResource {

    private final CropCatgoryService cropCatgoryService;

    public CropCatgoryResource(final CropCatgoryService cropCatgoryService) {
        this.cropCatgoryService = cropCatgoryService;
    }

    @GetMapping
    public ResponseEntity<List<CropCatgoryDTO>> getAllCropCatgories() {
        return ResponseEntity.ok(cropCatgoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CropCatgoryDTO> getCropCatgory(
            @PathVariable(name = "categoryId") final Long categoryId) {
        return ResponseEntity.ok(cropCatgoryService.get(categoryId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCropCatgory(
            @RequestBody @Valid final CropCatgoryDTO cropCatgoryDTO) {
        final Long createdCategoryId = cropCatgoryService.create(cropCatgoryDTO);
        return new ResponseEntity<>(createdCategoryId, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Long> updateCropCatgory(
            @PathVariable(name = "categoryId") final Long categoryId,
            @RequestBody @Valid final CropCatgoryDTO cropCatgoryDTO) {
        cropCatgoryService.update(categoryId, cropCatgoryDTO);
        return ResponseEntity.ok(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCropCatgory(
            @PathVariable(name = "categoryId") final Long categoryId) {
        cropCatgoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

}
