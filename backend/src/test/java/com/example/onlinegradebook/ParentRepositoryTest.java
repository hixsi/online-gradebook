package com.example.onlinegradebook.repositories;

import com.example.onlinegradebook.model.Parent;
import com.example.onlinegradebook.model.Parent;
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
public class ParentRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Test
    void findAllByNameTest() {
        String name = "Iordan Georgiev";
        User user1 = userRepository.getById(1);
        user1.setUsername(name);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentRepository.getById(1));
        studentList.add(studentRepository.getById(2));

        Parent Parent1 = new Parent(user1,studentList);

        testEntityManager.persistAndFlush(Parent1);

        User user2 = userRepository.getById(2);
        user2.setUsername(name);
        Parent Parent2 = new Parent(user2 ,studentList);
        testEntityManager.persistAndFlush(Parent2);
        Assertions.assertEquals(parentRepository.findAllByName(name).size(), 2);

    }

    @Test
    void findAllByEmptyNameTest() {
        String name = "Iordan Georgiev";
        User user = userRepository.getById(1);
        user.setUsername(name);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentRepository.getById(1));
        studentList.add(studentRepository.getById(2));
        Parent Parent  = new Parent(user,studentList);
        name = "";
        Assertions.assertEquals(parentRepository.findAllByName(name).size(), 0);
    }

    @Test
    void findAllByNotFoundNameTest() {
        String name = "Iordan Georgiev";
        User user = userRepository.getById(1);
        user.setUsername(name);
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentRepository.getById(1));
        studentList.add(studentRepository.getById(2));
        Parent Parent  = new Parent(user,studentList);

        name = "Dimcho Debelqnov";
        Assertions.assertEquals(parentRepository.findAllByName(name).size(), 0);
    }

    @Test
    public void save() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentRepository.getById(1));
        studentList.add(studentRepository.getById(2));
        Parent Parent = new Parent(userRepository.getById(1),studentList);

        parentRepository.save(Parent);
        Parent found = parentRepository.findById(Parent.getId()).get();
        assertEquals(Parent.getId(), found.getId());
    }
    @Test
    public void delete() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentRepository.getById(1));
        studentList.add(studentRepository.getById(2));
        Parent Parent = new Parent(userRepository.getById(1),studentList);
        parentRepository.save(Parent);
        parentRepository.deleteById(Parent.getId());
        assertEquals(parentRepository.findAll().size(), 0);
    }
}
