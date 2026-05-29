package com.example.demo.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Course;
import com.example.demo.domain.Student;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.services.CourseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final StudentRepository studentRepository;

    // GET ALL
    @GetMapping
    public List<Course> getAll() {
        return courseService.findAll();
    }

    // GET BY STUDENT
    @GetMapping("/student/{studentId}")
    public List<Course> getByStudent(@PathVariable Long studentId) {
        return courseService.findByStudentId(studentId);
    }

    // CREATE
    @PostMapping
    public Course create(@RequestBody Course course) {
        // Use getReferenceById so Hibernate treats it as an existing entity
        // (avoids detached/transient entity exceptions)
        if (course.getStudent() != null && course.getStudent().getId() != null) {
            Student studentRef = studentRepository.getReferenceById(course.getStudent().getId());
            course.setStudent(studentRef);
        }
        return courseService.save(course);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course course) {
        if (course.getStudent() != null && course.getStudent().getId() != null) {
            Student studentRef = studentRepository.getReferenceById(course.getStudent().getId());
            course.setStudent(studentRef);
        }
        return courseService.update(id, course);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}