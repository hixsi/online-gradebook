package com.example.onlinegradebook.repositories;

import com.example.onlinegradebook.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
  List<Student> findAllByName(String name);
}
