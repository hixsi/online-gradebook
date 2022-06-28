package com.example.onlinegradebook.services.implementations;


import com.example.onlinegradebook.model.School;
import com.example.onlinegradebook.repositories.SchoolRepository;
import com.example.onlinegradebook.services.SchoolService;
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
public class SchoolServiceTest {
    @MockBean
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolService schoolService;

    @Test
    void getSchoolById() {
        int schoolID = 1;

        School school = new School();
        school.setId(schoolID);

        Mockito.when(schoolRepository.findById(schoolID))
                .thenReturn(Optional.of(school));

        School school_ = schoolService.findSchoolById(schoolID);
        Assertions.assertEquals(school_.getId(),school.getId());

    }

    @Test
    public void addSchool() {
        School school = new School();
        school.setId(1);
        Mockito.when(schoolRepository.save(school)).thenReturn(school);
        Assertions.assertEquals(school,schoolService.addSchool(school));

    }

    @Test
    public void deleteSchool() {
        School school = new School();
        school.setId(1);
        schoolService.deleteSchool(school.getId());
        Mockito.verify(schoolRepository).deleteById(school.getId());

    }
}
