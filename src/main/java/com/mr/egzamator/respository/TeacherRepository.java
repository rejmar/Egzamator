package com.mr.egzamator.respository;

import com.mr.egzamator.model.Subject;
import com.mr.egzamator.model.Teacher;
import com.mr.egzamator.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query(value = "SELECT * FROM TEACHER t INNER JOIN USER u ON u.id = t.user_id WHERE u.user_identity = ?1", nativeQuery = true)
    Optional<Teacher> findByUserId(String userId);
}
