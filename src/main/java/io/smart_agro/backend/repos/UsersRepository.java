package io.smart_agro.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import io.smart_agro.backend.domain.Users;


public interface UsersRepository extends JpaRepository<Users, Long> {
    
     @Query("SELECT u FROM Users u WHERE u.username=?1")
    Users getByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Users u set u.password = ?1 where u.userId= ?2")
    int setchangePasswordForUser(String password, Long userid);
}
