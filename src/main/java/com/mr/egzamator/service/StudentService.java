package com.mr.egzamator.service;

import com.mr.egzamator.model.Student;
import com.mr.egzamator.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
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


}
