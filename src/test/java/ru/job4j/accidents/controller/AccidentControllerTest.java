package ru.job4j.accidents.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.mockito.ArgumentCaptor;
import org.springframework.test.web.servlet.ResultActions;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentServiceSpringData;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleServiceSpringData;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AccidentController.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentServiceSpringData accidentService;

    @MockBean
    private AccidentTypeService accidentTypeService;

    @MockBean
    private RuleServiceSpringData ruleService;

    @Test
    @WithMockUser
    void viewAccidentList() throws Exception {
        this.mockMvc.perform(get("/accident/accidentListPage"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/accidentList"));
    }

    @Test
    @WithMockUser
    void viewCreateAccident() throws Exception {
        this.mockMvc.perform(get("/accident/createAccidentPage"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/createAccident"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        AccidentType accidentType = new AccidentType(1, "name");
        Accident accident = new Accident(5, "name", "text",
                "address", accidentType, Set.of(new Rule()));
        mockMvc.perform(post("/accident/saveAccident")


                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argumentAccident = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).create(argumentAccident.capture());
        assertThat(argumentAccident.getValue()).isEqualTo(accident);

    }

    private String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}