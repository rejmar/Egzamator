package com.mr.egzamator.respository;

import com.mr.egzamator.model.SubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher,Integer> {
}
