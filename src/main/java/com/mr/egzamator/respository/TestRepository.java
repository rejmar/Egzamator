package com.mr.egzamator.respository;

import com.mr.egzamator.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test,Integer>{
}
