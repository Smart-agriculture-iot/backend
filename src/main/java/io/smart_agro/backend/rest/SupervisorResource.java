package io.smart_agro.backend.rest;

import io.smart_agro.backend.model.SupervisorDTO;
import io.smart_agro.backend.service.SupervisorService;
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
@RequestMapping(value = "/api/supervisors", produces = MediaType.APPLICATION_JSON_VALUE)
public class SupervisorResource {

    private final SupervisorService supervisorService;

    public SupervisorResource(final SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping
    public ResponseEntity<List<SupervisorDTO>> getAllSupervisors() {
        return ResponseEntity.ok(supervisorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupervisorDTO> getSupervisor(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(supervisorService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSupervisor(
            @RequestBody @Valid final SupervisorDTO supervisorDTO) {
        final Long createdId = supervisorService.create(supervisorDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSupervisor(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final SupervisorDTO supervisorDTO) {
        supervisorService.update(id, supervisorDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSupervisor(@PathVariable(name = "id") final Long id) {
        supervisorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
