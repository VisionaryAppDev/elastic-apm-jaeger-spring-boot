package com.example.apm.controller;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import com.example.apm.model.Student;
import com.example.apm.repository.StudentRepository;
import com.example.apm.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
public class TestController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping("/test")
    public Object test() {
        MDC.put("trace.id", ElasticApm.currentTransaction().getTraceId());
        MDC.put("transaction.id", ElasticApm.currentTransaction().getId());

        Student student = new Student();
        student.setName("Name: " + Math.random());
        student.setDob(new Date());
        studentRepository.findAll();


        studentService.random();
        studentService.request();


        /// Random Error
        if ((int) (Math.random() * 10) > 8) {
            throw new RuntimeException("Error" + Math.random());
        }

        String transactionId = ElasticApm.currentTransaction().getId();
        String spanId = ElasticApm.currentSpan().getId();
        String traceId = ElasticApm.currentSpan().getTraceId();

        return String.format("=> Transaction ID: %s, Span ID: %s, Trace ID: %s", transactionId, spanId, traceId );
    }
}
