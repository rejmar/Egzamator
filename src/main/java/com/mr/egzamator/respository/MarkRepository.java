package com.mr.egzamator.respository;

import com.mr.egzamator.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Long> {
}
