package com.example.onlinegradebook.controllers;

import com.example.onlinegradebook.controller.SchoolController;

import com.example.onlinegradebook.services.SchoolService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@ExtendWith(SpringExtension.class)
@WebMvcTest(SchoolController.class)
public class SchoolControllerTest {
    @MockBean
    private SchoolService schoolService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showSchools() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/schools"))
                .andExpect(status().isOk());
    }
}
