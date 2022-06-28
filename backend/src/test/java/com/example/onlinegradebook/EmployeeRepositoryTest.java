package com.example.onlinegradebook.repositories;

import com.example.onlinegradebook.model.Employee;
import com.example.onlinegradebook.model.School;
import com.example.onlinegradebook.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SchoolRepository schoolRepository;



    @Test
    void findAllByQualificationTest() {

        Employee employee1 = new Employee();
        employee1.setQualification("Teacher");
        employee1.setId(1);


        testEntityManager.persistAndFlush(employee1);

        Employee employee2 = new Employee();
        employee2.setQualification("Teacher");
        employee2.setId(2);
        testEntityManager.persistAndFlush(employee2);

        assertThat(employeeRepository.findAllByQualification("Teacher").size()).isEqualTo(2);
    }

    @Test
    public void testFindAll() {
        Employee employee1 = new Employee(new User(),"Teacher",1200,schoolRepository.getById(1));
        employeeRepository.save(employee1);
        List<Employee> result = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 1);
    }

    @Test
    void findAllByEmptyQualificationTest() {
        Employee employee = new Employee(new User(),"Teacher",1200,schoolRepository.getById(1));
        String qualification = "";
        assertThat(employeeRepository.findAllByQualification(qualification).size()).isEqualTo(0);
    }

    @Test
    void findAllByNotFoundQualificationTest() {
        String qualification = "Teacher";
        Employee employee = new Employee();
        employee.setQualification(qualification);

        qualification = "no qualification";
        assertThat(employeeRepository.findAllByQualification(qualification).size()).isEqualTo(0);
    }

    @Test
    public void save() {
        Employee employee = new Employee();

        employee.setId(1);
        employee.setSchool(new School("Ivan Vazov", "Varna",null));

        employeeRepository.save(employee);
        Assertions.assertNotNull(employeeRepository.findById(employee.getId()));
    }
    @Test
    public void delete() {

        Employee employee = new Employee(new User(),"Teacher",1200,schoolRepository.getById(1));
        employeeRepository.save(employee);
        employeeRepository.deleteById(employee.getId());
        assertEquals(employeeRepository.findAll().size(), 0);
    }
}