package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import io.smart_agro.backend.domain.Cooperative;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CooperativeRepository extends JpaRepository<Cooperative, Long> {

     @Query("SELECT c FROM Cooperative c WHERE c.coName=?1")
    Cooperative getByCoName(String coName);
}
