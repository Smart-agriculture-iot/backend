package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import io.smart_agro.backend.domain.HistoricalData;


public interface HistoricalDataRepository extends JpaRepository<HistoricalData, Long> {
}
