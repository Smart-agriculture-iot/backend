package io.smart_agro.backend.rest;

import io.smart_agro.backend.model.SensorDataDTO;
import io.smart_agro.backend.domain.SensorData;
import io.smart_agro.backend.service.SensorDataService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.smart_agro.backend.model.DeviceDTO;
import io.smart_agro.backend.domain.Device;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping(value = "/api/v1/sensordata", produces = MediaType.APPLICATION_JSON_VALUE)
public class SensorDataResource {
   
  
    private final SensorDataService sensorDataService;
  private final DeviceService deviceService;
    public SensorDataResource(final SensorDataService sensorDataService,final DeviceService deviceService) {
        this.sensorDataService = sensorDataService;
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<SensorDataDTO>> getAllSensorDatas() {
        return ResponseEntity.ok(sensorDataService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDataDTO> getSensorData(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(sensorDataService.get(id));
    }

 @GetMapping("/latest")
    public ResponseEntity<SensorData> getSensorlatestData() {
        return ResponseEntity.ok(sensorDataService.getlast());
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSensorData(
            @RequestBody @Valid final SensorDataDTO sensorDataDTO) {
        final Long createdId = sensorDataService.create(sensorDataDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSensorData(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final SensorDataDTO sensorDataDTO) {
        sensorDataService.update(id, sensorDataDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSensorData(@PathVariable(name = "id") final Long id) {
        sensorDataService.delete(id);
        return ResponseEntity.noContent().build();
    }


@GetMapping("/sensordata/add")
  public String saveheader(
      @RequestParam(value = "humidity") String humidity, @RequestParam(value = "temperature") String temperature, @RequestParam(value = "soilmoisture") String soilmoisture, @RequestParam(value = "lat") String lat, @RequestParam(value = "lng") String lng, @RequestParam(value = "deviceId") Long deviceId, @RequestParam(value = "israining") String israining, @RequestParam(value = "voltage") String voltage){
// https://rwandasmartagro.rw:5000/api/v1/sensordata?temperature=nan&humidity=nan&soilmoisture=20.00&iSraining=1&serialNumber=Device001&longtitude=invalid&latitude=invalid&voltage=233.00

// System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM "+voltage);
deviceService.upDateBatterLife(voltage,deviceId);
SensorDataDTO sensorDataDTO=new SensorDataDTO();
sensorDataDTO.setHumidity(humidity);
sensorDataDTO.setTemperature(temperature);
sensorDataDTO.setSoilmoisture(soilmoisture);
sensorDataDTO.setLat(lat);
sensorDataDTO.setLng(lng);
sensorDataDTO.setDeviceId(deviceId.toString());
sensorDataDTO.setIsRaining(israining);
sensorDataDTO.setSynced(false);
sensorDataDTO.setBattery(voltage);
sensorDataService.create(sensorDataDTO);

return "Data saved successfully";

}
}
