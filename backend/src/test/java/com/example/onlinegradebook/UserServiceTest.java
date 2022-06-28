package com.example.onlinegradebook.services.implementations;


import com.example.onlinegradebook.constant.RoleType;
import com.example.onlinegradebook.model.User;
import com.example.onlinegradebook.repositories.UserRepository;
import com.example.onlinegradebook.services.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userServices;

    User user;
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        user = new User("Hristo Stoqnov", "9902125678","Address", RoleType.TEACHER,null, null,null );
        user.setId(1);

    }

    @Test
    public void updateUser() throws Exception {

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User user1 = userServices.updateUser(user);
        Assertions.assertNotNull(user1);

    }

    @Test
    public void getUser(){
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User user1 = userServices.findUserById(1);
        Assertions.assertNotNull(user1);
    }




}