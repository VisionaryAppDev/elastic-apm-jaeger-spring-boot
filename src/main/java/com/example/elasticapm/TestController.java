package com.example.elasticapm;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import com.example.elasticapm.model.Student;
import com.example.elasticapm.repository.StudentRepository;
import com.example.elasticapm.service.StudentService;
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
        student.setName("Nmae"+ Math.random());
        student.setDob(new Date());
        studentRepository.findAll();


        studentService.random();
        studentService.request();



//        if ((int) (Math.random() * 10) > 5) {
//            span.setOutcome(Outcome.FAILURE);
//            throw new RuntimeException("Error" + Math.random());
//        }

        return " ::  " + UUID.randomUUID();
    }
}
