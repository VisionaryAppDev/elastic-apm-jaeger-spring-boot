package com.example.elasticapm.repository;

import com.example.elasticapm.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
