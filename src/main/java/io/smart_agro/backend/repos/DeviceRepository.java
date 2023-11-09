package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import io.smart_agro.backend.domain.Device;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
     @Transactional
    @Modifying
    @Query("update Device d set d.battery = ?1 where d.deviceId= ?2")
    int upDateBatterLife(String battery, Long deviceId);
}
