package com.mr.egzamator.respository;

import com.mr.egzamator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    @Query(value = "SELECT * FROM QUESTION q INNER JOIN TEST t ON t.id = q.test_id WHERE t.name = ?1", nativeQuery = true)
    List<Question> getQuestions(String testName);
}
