package com.mr.egzamator.service;

import com.mr.egzamator.model.Student;
import com.mr.egzamator.respository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {
    @Resource
    StudentRepository studentRepository;

    public Student getStudentById(int id){
        return studentRepository.findOne(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }


}
