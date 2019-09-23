package com.mr.egzamator.respository;

import com.mr.egzamator.model.Student;
import com.mr.egzamator.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT * FROM STUDENT s INNER JOIN USER u ON u.id = s.user_id WHERE u.user_identity = ?1", nativeQuery = true)
    Optional<Student> findByUserId(String userId);


}
