package com.mr.egzamator.respository;

import com.mr.egzamator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query(value = "SELECT * FROM QUESTION q INNER JOIN TEST t ON t.id = q.test_id WHERE t.name = ?1", nativeQuery = true)
    Set<Question> getQuestions(String testName);

    @Query(value = "SELECT * FROM QUESTION q INNER JOIN TEST t ON t.id=q.test_id WHERE t.name = ?1 AND q.id = ?2", nativeQuery = true)
    Optional<Question> findByTestNameAndId(String name, Integer id);
}
