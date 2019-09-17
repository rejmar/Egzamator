package com.mr.egzamator.respository;

import com.mr.egzamator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT DISTINCT(role) FROM USER u INNER JOIN ROLE r ON u.role_id = r.role_id WHERE u.user_identity = ?1", nativeQuery = true)
    String findUserRole(String userId);

    Optional<User> findByIndexNumber(Integer indexNumber);

    Optional<User> findByEmailAndIndexNumber(String email, Integer indexNumber);

    @Query(value = "SELECT * FROM USER u WHERE u.email = ?1 OR u.index_number = ?2", nativeQuery = true)
    List<User> getAllUsersByEmailOrIndexNumber(String email, Integer indexNumber);

    Optional<User> findByUserIdentity(String userId);
}