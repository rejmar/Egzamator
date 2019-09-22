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
    Set<Test> findTestsByUserId(String userId);
}
