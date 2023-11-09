package io.smart_agro.backend.rest;

import io.smart_agro.backend.model.DeviceDTO;
import io.smart_agro.backend.service.DeviceService;
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
@RequestMapping(value = "/api/devices", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceResource {

    private final DeviceService deviceService;

    public DeviceResource(final DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> getAllDevices() {
        return ResponseEntity.ok(deviceService.findAll());
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceDTO> getDevice(
            @PathVariable(name = "deviceId") final Long deviceId) {
        return ResponseEntity.ok(deviceService.get(deviceId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDevice(@RequestBody @Valid final DeviceDTO deviceDTO) {
        final Long createdDeviceId = deviceService.create(deviceDTO);
        return new ResponseEntity<>(createdDeviceId, HttpStatus.CREATED);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<Long> updateDevice(@PathVariable(name = "deviceId") final Long deviceId,
            @RequestBody @Valid final DeviceDTO deviceDTO) {
        deviceService.update(deviceId, deviceDTO);
        return ResponseEntity.ok(deviceId);
    }

    @DeleteMapping("/{deviceId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDevice(@PathVariable(name = "deviceId") final Long deviceId) {
        deviceService.delete(deviceId);
        return ResponseEntity.noContent().build();
    }

}
