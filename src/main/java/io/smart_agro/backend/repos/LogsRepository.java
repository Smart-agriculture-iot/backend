package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import io.smart_agro.backend.domain.Logs;


public interface LogsRepository extends JpaRepository<Logs, Long> {
}
