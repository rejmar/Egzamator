package com.mr.egzamator.respository;

import com.mr.egzamator.model.Subject;
import com.mr.egzamator.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer>{
    Optional<Subject> findByName(String subjectName);
}
