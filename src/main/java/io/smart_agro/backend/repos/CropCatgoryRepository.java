package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import io.smart_agro.backend.domain.CropCatgory;


public interface CropCatgoryRepository extends JpaRepository<CropCatgory, Long> {
}
