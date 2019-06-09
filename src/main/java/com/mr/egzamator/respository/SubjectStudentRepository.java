package com.mr.egzamator.respository;

import com.mr.egzamator.model.SubjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectStudentRepository extends JpaRepository<SubjectStudent,Integer>{
}
