package com.mr.egzamator.respository;

import com.mr.egzamator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
