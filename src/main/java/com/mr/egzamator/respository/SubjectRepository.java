package com.mr.egzamator.respository;

import com.mr.egzamator.model.Subject;
import com.mr.egzamator.model.Teacher;
import com.mr.egzamator.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer>{
    Optional<Subject> findByName(String subjectName);

    @Query(value = "SELECT * FROM subject s\n" +
            "INNER JOIN TEST tst ON tst.subject_id=s.id\n" +
            "INNER JOIN teacher t ON t.id=tst.teacher_id\n" +
            "INNER JOIN USER u ON u.id = t.user_id WHERE u.user_identity = ?1", nativeQuery = true)
    Set<Subject> findTeacherSubjectsTestsByUserId(String userId);

    @Query(value = "SELECT * FROM subject sb\n" +
            "INNER JOIN TEST tst ON tst.subject_id=sb.id\n" +
            "INNER JOIN STUDENT_SUBJECT ss ON ss.subject_id=sb.id\n" +
            "INNER JOIN student st ON st.id=ss.student_id\n" +
            "INNER JOIN USER u ON u.id = st.user_id WHERE u.user_identity = ?1", nativeQuery = true)
    Set<Subject> findStudentSubjectsTestsByUserId(String userId);

    @Query(value = "SELECT * FROM subject s " +
            "INNER JOIN teacher_subject ts ON s.id=ts.subject_id " +
            "INNER JOIN teacher t ON ts.teacher_id=t.id " +
            "INNER JOIN user u ON u.id=t.user_id " +
            "WHERE u.user_identity=?1", nativeQuery = true)
    Set<Subject> findTeacherSubjectsByUserId(String userId);

    @Query(value = "SELECT * FROM subject sb " +
            "INNER JOIN STUDENT_SUBJECT ss ON ss.subject_id=sb.id\n" +
            "INNER JOIN STUDENT st ON ss.student_id=st.id " +
            "INNER JOIN user u ON u.id=st.user_id " +
            "WHERE u.user_identity=?1", nativeQuery = true)
    Set<Subject> findStudentSubjectsByUserId(String userId);
}
