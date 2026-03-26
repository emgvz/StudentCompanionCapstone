package com.example.demo.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Assessment;
import com.example.demo.services.AssessmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService service;

    @GetMapping
    public List<Assessment> getAll() {
        return service.findAll();
    }

    @GetMapping("/{courseId}")
    public List<Assessment> getByCourse(@PathVariable Long courseId) {
        return service.findByCourse(courseId);
    }

    @PostMapping
    public Assessment create(@RequestBody Assessment a) {
        return service.save(a);
    }
}
