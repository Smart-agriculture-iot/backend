package io.smart_agro.backend.rest;

import io.smart_agro.backend.model.HistoricalDataDTO;
import io.smart_agro.backend.service.HistoricalDataService;
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
@RequestMapping(value = "/api/historicalDatas", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoricalDataResource {

    private final HistoricalDataService historicalDataService;

    public HistoricalDataResource(final HistoricalDataService historicalDataService) {
        this.historicalDataService = historicalDataService;
    }

    @GetMapping
    public ResponseEntity<List<HistoricalDataDTO>> getAllHistoricalDatas() {
        return ResponseEntity.ok(historicalDataService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricalDataDTO> getHistoricalData(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(historicalDataService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createHistoricalData(
            @RequestBody @Valid final HistoricalDataDTO historicalDataDTO) {
        final Long createdId = historicalDataService.create(historicalDataDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateHistoricalData(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final HistoricalDataDTO historicalDataDTO) {
        historicalDataService.update(id, historicalDataDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteHistoricalData(@PathVariable(name = "id") final Long id) {
        historicalDataService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
