package com.example.onlinegradebook.repositories;

import com.example.onlinegradebook.model.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class SchoolRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SchoolRepository schoolRepository;
    @Test
    void findAllByNameTest() {
        String name = "Nayden Gerov";
        School school1 = new School();
        school1.setName(name);
        testEntityManager.persistAndFlush(school1);

        School school2 = new School();
        school2.setName(name);
        testEntityManager.persistAndFlush(school2);
        Assertions.assertEquals(schoolRepository.findAllByName(name).size(), 2);

    }

    @Test
    void findAllByEmptyNameTest() {
        String name = "Nayden Gerov";
        School school = new School();
        school.setName(name);
        name = "";
        Assertions.assertEquals(schoolRepository.findAllByName(name).size(), 0);

    }

    @Test
    void findAllByNotFoundNameTest() {
        String name = "Hayden Gerov";
        School school = new School();
        school.setName(name);

        name = "Dimcho Debelqnov";
        Assertions.assertEquals(schoolRepository.findAllByName(name).size(), 0);
    }

    @Test
    public void save() {
        String name = "Peter Ivanov";
        School school = new School();
        school.setName(name);
        schoolRepository.save(school);
        School found = schoolRepository.findById(school.getId()).get();
        assertEquals(school.getId(), found.getId());
    }
    @Test
    public void delete() {
        String name = "Peter Ivanov";
        School school = new School();
        school.setName(name);
        schoolRepository.save(school);
        schoolRepository.deleteById(school.getId());
        assertEquals(schoolRepository.findAll().size(), 0);
    }

}