package com.mr.egzamator.respository;

import com.mr.egzamator.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Long> {
}
