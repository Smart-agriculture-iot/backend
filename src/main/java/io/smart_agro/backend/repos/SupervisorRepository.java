package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import io.smart_agro.backend.domain.Supervisor;


public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
}
