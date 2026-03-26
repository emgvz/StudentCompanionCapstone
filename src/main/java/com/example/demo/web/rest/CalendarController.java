package com.example.demo.web.rest;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Assessment;
import com.example.demo.repositories.AssessmentRepository;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    @Autowired
    private AssessmentRepository assessmentRepo;

    @GetMapping
    public List<Assessment> getCalendarEvents() {

        List<Assessment> list = assessmentRepo.findAll();

        // Sort events by due date (Phase 4.3)
        list.sort(Comparator.comparing(Assessment::getDueDate));

        return list;
    }
}
