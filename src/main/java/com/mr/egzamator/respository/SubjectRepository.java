package com.mr.egzamator.respository;

import com.mr.egzamator.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer>{
    Optional<Subject> findByName(String subjectName);
}
