package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.smart_agro.backend.domain.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    
    
    @Query("SELECT r FROM Role r WHERE r.roleName=?1")
    Role getByRolename(String roleName);

}
