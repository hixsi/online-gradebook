package com.example.onlinegradebook.repositories;

import com.example.onlinegradebook.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  List<Employee> findAllByQualification(String employee);
}
