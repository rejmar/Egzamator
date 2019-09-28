package com.mr.egzamator.respository;

import com.mr.egzamator.model.Teacher;
import com.mr.egzamator.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TestRepository extends JpaRepository<Test,Integer>{

    @Query(value = "SELECT * FROM TEST ts INNER JOIN TEACHER tr ON ts.teacher_id=tr.id INNER JOIN USER u ON u.id=tr.user_id WHERE u.user_identity=?1 AND ts.name = ?2", nativeQuery = true)
    Optional<Test> findByUserIdAndTestName(String userId, String testName);

    @Query(value = "SELECT * FROM TEST ts INNER JOIN TEACHER tr ON ts.teacher_id=tr.id INNER JOIN USER u ON u.id=tr.user_id WHERE u.user_identity=?1", nativeQuery = true)
    Set<Test> findByUserId(String userId);

    @Query(value = "SELECT * FROM TEST tst\n" +
            "INNER JOIN SUBJECT s ON tst.subject_id=s.id\n" +
            "INNER JOIN TEACHER t ON t.id=tst.teacher_id\n" +
            "INNER JOIN USER u ON u.id = t.user_id WHERE u.user_identity = ?1", nativeQuery = true)
    Set<Test> findTeacherTestsByUserId(String userId);

    @Query(value = "SELECT * FROM TEST tst\n" +
            "INNER JOIN SUBJECT sb ON tst.subject_id=sb.id\n" +
            "INNER JOIN STUDENT_SUBJECT ss ON ss.subject_id = sb.id\n" +
            "INNER JOIN STUDENT st ON st.id=ss.student_id\n" +
            "INNER JOIN USER u ON u.id = st.user_id WHERE u.user_identity = ?1", nativeQuery = true)
    Set<Test> findStudentTestsByUserId(String userId);

    @Query(value = "SELECT * FROM TEST tst\n" +
            "INNER JOIN SUBJECT sb ON tst.subject_id=sb.id\n" +
            "INNER JOIN STUDENT_SUBJECT ss ON ss.subject_id = sb.id\n" +
            "INNER JOIN STUDENT st ON st.id=ss.student_id\n" +
            "INNER JOIN MARK m ON m.student_id = st.id\n" +
            "INNER JOIN USER u ON u.id = st.user_id WHERE u.user_identity = ?1 AND m.test_id=tst.id", nativeQuery = true)
    Set<Test> findSolvedStudentTestsByUserId(String userId);

    @Query(value = "SELECT * FROM TEST tst WHERE tst.name=?1", nativeQuery = true)
   Optional<Test> findByName(String name);
}
