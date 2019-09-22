package com.mr.egzamator.service;

import com.mr.egzamator.model.Student;
import com.mr.egzamator.model.Subject;
import com.mr.egzamator.model.Teacher;
import com.mr.egzamator.respository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(int id){
        return studentRepository.getOne(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }


    public Set<Subject> getSubjects(String userId){
        log.info("Looking for user " + userId);
        Optional<Student> oTeacher = studentRepository.findByUserId(userId);
        return oTeacher.map(Student::getSubjects).orElse(null);
    }
}
