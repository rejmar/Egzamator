package com.mr.egzamator.respository;

import com.mr.egzamator.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
