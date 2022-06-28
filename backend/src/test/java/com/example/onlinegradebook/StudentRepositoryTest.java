package com.example.onlinegradebook.repositories;

import com.example.onlinegradebook.model.Student;
import com.example.onlinegradebook.model.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolClassRepository schoolClassRepository;
    @Test
    void findAllByNameTest() {
        String name = "Iordan Georgiev";
        User user1 = userRepository.getById(1);
        user1.setUsername(name);
        Student Student1 = new Student(user1,schoolClassRepository.getById(1));

        testEntityManager.persistAndFlush(Student1);
        User user2 = userRepository.getById(2);
        user2.setUsername(name);
        Student Student2 = new Student(user2 ,schoolClassRepository.getById(2));
        testEntityManager.persistAndFlush(Student2);
        Assertions.assertEquals(studentRepository.findAllByName(name).size(), 2);

    }

    @Test
    void findAllByEmptyNameTest() {
        String name = "Iordan Georgiev";
        User user = userRepository.getById(1);
        user.setUsername(name);
        Student student  = new Student(user,schoolClassRepository.getById(1));
        name = "";
        Assertions.assertEquals(studentRepository.findAllByName(name).size(), 0);
    }

    @Test
    void findAllByNotFoundNameTest() {
        String name = "Iordan Georgiev";
        User user = userRepository.getById(1);
        user.setUsername(name);
        Student student  = new Student(user,schoolClassRepository.getById(1));

        name = "Dimcho Debelqnov";
        Assertions.assertEquals(studentRepository.findAllByName(name).size(), 0);
    }

    @Test
    public void save() {
        Student student = new Student(userRepository.getById(1),schoolClassRepository.getById(1));

        studentRepository.save(student);
        Student found = studentRepository.findById(student.getId()).get();
        assertEquals(student.getId(), found.getId());
    }
    @Test
    public void delete() {
        Student student = new Student(userRepository.getById(1),schoolClassRepository.getById(1));
        studentRepository.save(student);
        studentRepository.deleteById(student.getId());
        assertEquals(studentRepository.findAll().size(), 0);
    }
}
