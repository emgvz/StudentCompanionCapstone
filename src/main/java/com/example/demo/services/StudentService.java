package com.example.demo.services;

import java.util.List;

import com.example.demo.domain.Student;

public interface StudentService {
    List<Student> findAll();
    Student save(Student student);
}
