package com.dongsamo.jogumanClone.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc
@SpringBootTest
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    //spring security 테스트로 해야함
//    @Test
//    void admin() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
}