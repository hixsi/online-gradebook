package com.example.onlinegradebook.services.implementations;

import com.example.onlinegradebook.model.Employee;
import com.example.onlinegradebook.repositories.EmployeeRepository;
import com.example.onlinegradebook.repositories.SchoolRepository;
import com.example.onlinegradebook.repositories.UserRepository;
import com.example.onlinegradebook.services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServicesTest {
    @MockBean
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private EmployeeService employeeServices;

    Employee employee;

    @BeforeEach
    public void init(){
        employee = new Employee(userRepository.findById(1).get(), "Teacher",1200,schoolRepository.getById(1)  );
        employee.setId(1);
    }


    @Test
    void getEmployeeById() {

        Mockito.when(employeeRepository.findById(1))
                .thenReturn(Optional.of(employee));
        Employee employee1 = employeeServices.findEmployeeById(1);
        Assertions.assertEquals(employee1.getId(),employee.getId());
    }



    @Test
    public void updateEmployee() throws Exception {

        Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        employeeServices.updateEmployee(employee);
        Assertions.assertNotNull(employee);

    }

    @Test
    public void addEmployee() {

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Assertions.assertEquals(employee, employeeServices.addEmployee(employee));

    }

    @Test
    public void deleteEmployee() {

        employeeServices.deleteEmployee(employee.getId());
        Mockito.verify(employeeRepository).deleteById(employee.getId());

    }


}