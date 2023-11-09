package io.smart_agro.backend.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.smart_agro.backend.domain.Device;
import io.smart_agro.backend.model.DeviceDTO;
import io.smart_agro.backend.repos.DeviceRepository;
import io.smart_agro.backend.util.NotFoundException;


@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceDTO> findAll() {
        final List<Device> devices = deviceRepository.findAll(Sort.by("deviceId"));
        return devices.stream()
                .map(device -> mapToDTO(device, new DeviceDTO()))
                .toList();
    }

    public DeviceDTO get(final Long deviceId) {
        return deviceRepository.findById(deviceId)
                .map(device -> mapToDTO(device, new DeviceDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final DeviceDTO deviceDTO) {
        final Device device = new Device();
        mapToEntity(deviceDTO, device);
        return deviceRepository.save(device).getDeviceId();
    }

    public void update(final Long deviceId, final DeviceDTO deviceDTO) {
        final Device device = deviceRepository.findById(deviceId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(deviceDTO, device);
        deviceRepository.save(device);
    }

    public void delete(final Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    private DeviceDTO mapToDTO(final Device device, final DeviceDTO deviceDTO) {
        deviceDTO.setDeviceId(device.getDeviceId());
        deviceDTO.setBattery(device.getBattery());
        deviceDTO.setLongtitude(device.getLongtitude());
        deviceDTO.setLatitude(device.getLatitude());
        deviceDTO.setStatus(device.getStatus());
        deviceDTO.setCoId(device.getCoId());
        return deviceDTO;
    }

    private Device mapToEntity(final DeviceDTO deviceDTO, final Device device) {
        device.setBattery(deviceDTO.getBattery());
        device.setLongtitude(deviceDTO.getLongtitude());
        device.setLatitude(deviceDTO.getLatitude());
        device.setStatus(deviceDTO.getStatus());
        device.setCoId(deviceDTO.getCoId());
        return device;
    }

  public int upDateBatterLife(String battery,Long deviceId){
      deviceRepository.upDateBatterLife(battery,deviceId);
      return 1;
    }
}
