package com.mr.egzamator.respository;

import com.mr.egzamator.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Integer>{
}
