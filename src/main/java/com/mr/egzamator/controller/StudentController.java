package com.mr.egzamator.controller;

import com.mr.egzamator.model.Student;
import com.mr.egzamator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/getStudent/{id}")
    public Student getStudent(@PathVariable int id){
        return studentService.getStudentById(id);
//        if (student != null) {
//            return new ResponseEntity<Student>(student,HttpStatus.OK);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

    }

    @GetMapping(value = "/getAllStudents")
    public @ResponseBody List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/addStudent")
    public void saveStudent(@RequestBody Student student){
        Student newStudent = studentService.saveStudent(student);
    }
}
