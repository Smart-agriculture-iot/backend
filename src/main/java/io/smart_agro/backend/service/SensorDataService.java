package io.smart_agro.backend.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.smart_agro.backend.domain.SensorData;
import io.smart_agro.backend.model.SensorDataDTO;
import io.smart_agro.backend.repos.SensorDataRepository;
import io.smart_agro.backend.util.NotFoundException;


@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    public SensorDataService(final SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorDataDTO> findAll() {
        final List<SensorData> sensorDatas = sensorDataRepository.findAll(Sort.by("id"));
        return sensorDatas.stream()
                .map(sensorData -> mapToDTO(sensorData, new SensorDataDTO()))
                .toList();
    }


    public SensorDataDTO get(final Long id) {
        return sensorDataRepository.findById(id)
                .map(sensorData -> mapToDTO(sensorData, new SensorDataDTO()))
                .orElseThrow(NotFoundException::new);
    }

public SensorData getlast() {
        return sensorDataRepository.findTopByOrderByIdDesc();    
    }
    public Long create(final SensorDataDTO sensorDataDTO) {
        final SensorData sensorData = new SensorData();
        mapToEntity(sensorDataDTO, sensorData);
        return sensorDataRepository.save(sensorData).getId();
    }

    public void update(final Long id, final SensorDataDTO sensorDataDTO) {
        final SensorData sensorData = sensorDataRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(sensorDataDTO, sensorData);
        sensorDataRepository.save(sensorData);
    }

    public void delete(final Long id) {
        sensorDataRepository.deleteById(id);
    }

    private SensorDataDTO mapToDTO(final SensorData sensorData, final SensorDataDTO sensorDataDTO) {
        sensorDataDTO.setId(sensorData.getId());
        sensorDataDTO.setDeviceId(sensorData.getDeviceId());
        sensorDataDTO.setIsRaining(sensorData.getIsRaining());
        sensorDataDTO.setTemperature(sensorData.getTemperature());
        sensorDataDTO.setSoilmoisture(sensorData.getSoilmoisture());
        sensorDataDTO.setHumidity(sensorData.getHumidity());
        sensorDataDTO.setSynced(sensorData.getSynced());
        sensorDataDTO.setLat(sensorData.getLat());
        sensorDataDTO.setLng(sensorData.getLng());
        // sensorDataDTO.setBattery(sensorData.getBattery);
        return sensorDataDTO;
    }

    private SensorData mapToEntity(final SensorDataDTO sensorDataDTO, final SensorData sensorData) {
        sensorData.setDeviceId(sensorDataDTO.getDeviceId());
        sensorData.setIsRaining(sensorDataDTO.getIsRaining());
        sensorData.setTemperature(sensorDataDTO.getTemperature());
        sensorData.setSoilmoisture(sensorDataDTO.getSoilmoisture());
        sensorData.setHumidity(sensorDataDTO.getHumidity());
        sensorData.setSynced(sensorDataDTO.getSynced());
        sensorData.setLat(sensorDataDTO.getLat());
        sensorData.setLng(sensorDataDTO.getLng());
        //  sensorData.setBattery(sensorDataDTO.getBattery());
        return sensorData;
    }

}
