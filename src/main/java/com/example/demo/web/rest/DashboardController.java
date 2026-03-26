package com.example.demo.web.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Assessment;
import com.example.demo.repositories.AssessmentRepository;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private AssessmentRepository repo;

    /**
     * Get upcoming tasks (future deadlines)
     */
    @GetMapping("/upcoming")
    public List<Assessment> getUpcoming() {
        return repo.findByDueDateAfter(LocalDate.now());
    }

    /**
     * Get overdue tasks (past deadlines)
     */
    @GetMapping("/overdue")
    public List<Assessment> getOverdue() {
        return repo.findByDueDateBefore(LocalDate.now());
    }
}
