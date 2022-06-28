package com.example.onlinegradebook.services.implementations;

import com.example.onlinegradebook.model.Student;
import com.example.onlinegradebook.repositories.StudentRepository;
import com.example.onlinegradebook.services.StudentService;

import org.junit.jupiter.api.Assertions;
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
public class StudentServiceTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    public void updateStudent() throws Exception {
        Student student = new Student();
        student.setId(1);

        Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        studentService.updateStudent(student);
        Assertions.assertNotNull(student);

    }

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setId(1);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Assertions.assertEquals(student, studentService.addStudent(student));

    }

    @Test
    public void deleteStudent() {
        Student student = new Student();
        student.setId(1);
        studentService.deleteStudent(student.getId());
        Mockito.verify(studentRepository).deleteById(student.getId());

    }
    
}
