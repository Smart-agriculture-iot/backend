package io.smart_agro.backend.rest;

import io.smart_agro.backend.model.LogsDTO;
import io.smart_agro.backend.service.LogsService;
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
@RequestMapping(value = "/api/logss", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogsResource {

    private final LogsService logsService;

    public LogsResource(final LogsService logsService) {
        this.logsService = logsService;
    }

    @GetMapping
    public ResponseEntity<List<LogsDTO>> getAllLogss() {
        return ResponseEntity.ok(logsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogsDTO> getLogs(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(logsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLogs(@RequestBody @Valid final LogsDTO logsDTO) {
        final Long createdId = logsService.create(logsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateLogs(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final LogsDTO logsDTO) {
        logsService.update(id, logsDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLogs(@PathVariable(name = "id") final Long id) {
        logsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
