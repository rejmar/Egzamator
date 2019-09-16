package com.mr.egzamator.respository;

import com.mr.egzamator.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(String role);

    @Query(value = "SELECT * FROM ROLE WHERE role=?1", nativeQuery = true)
    Role findByName(String role);
}