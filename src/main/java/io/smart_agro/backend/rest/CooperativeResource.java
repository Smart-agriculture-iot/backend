package io.smart_agro.backend.rest;

import io.smart_agro.backend.model.CooperativeDTO;
import io.smart_agro.backend.service.CooperativeService;
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
@RequestMapping(value = "/api/cooperatives", produces = MediaType.APPLICATION_JSON_VALUE)
public class CooperativeResource {

    private final CooperativeService cooperativeService;

    public CooperativeResource(final CooperativeService cooperativeService) {
        this.cooperativeService = cooperativeService;
    }

    @GetMapping
    public ResponseEntity<List<CooperativeDTO>> getAllCooperatives() {
        return ResponseEntity.ok(cooperativeService.findAll());
    }

    @GetMapping("/{cooperativeId}")
    public ResponseEntity<CooperativeDTO> getCooperative(
            @PathVariable(name = "cooperativeId") final Long cooperativeId) {
        return ResponseEntity.ok(cooperativeService.get(cooperativeId));
    }

     @GetMapping("/name/{coname}")
    public ResponseEntity<CooperativeDTO> getCooperative(
            @PathVariable(name = "coname") final String coname) {
        return ResponseEntity.ok(cooperativeService.getByCoName(coname));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCooperative(
            @RequestBody @Valid final CooperativeDTO cooperativeDTO) {
        final Long createdCooperativeId = cooperativeService.create(cooperativeDTO);
        return new ResponseEntity<>(createdCooperativeId, HttpStatus.CREATED);
    }

    @PutMapping("/{cooperativeId}")
    public ResponseEntity<Long> updateCooperative(
            @PathVariable(name = "cooperativeId") final Long cooperativeId,
            @RequestBody @Valid final CooperativeDTO cooperativeDTO) {
        cooperativeService.update(cooperativeId, cooperativeDTO);
        return ResponseEntity.ok(cooperativeId);
    }

    @DeleteMapping("/{cooperativeId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCooperative(
            @PathVariable(name = "cooperativeId") final Long cooperativeId) {
        cooperativeService.delete(cooperativeId);
        return ResponseEntity.noContent().build();
    }

}
