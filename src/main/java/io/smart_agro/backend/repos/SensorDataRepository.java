package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import io.smart_agro.backend.domain.SensorData;


public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    SensorData findTopByOrderByIdDesc();
}
