package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void viewAccidentList() throws Exception {
        this.mockMvc.perform(get("/accidentListPage"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/accidentList"));
    }

    @Test
    @WithMockUser
    void viewCreateAccident() throws Exception {
        this.mockMvc.perform(get("/createAccidentPage"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/createAccident"));
    }


    @Test
    @WithMockUser
    void viewUpdateAccident() throws Exception {
        int id = 1;
        this.mockMvc.perform(get("/updateAccidentPage/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/updateAccident"));
    }

}