package com.udacity.dog.web;

import com.udacity.dog.service.DogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Base64;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(DogController.class)
public class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogService dogService;

    private String userAuth;

    private String managerAuth;

    @BeforeEach
    public void auth() {
        userAuth = "Basic " + Base64.getEncoder().encodeToString("pep:password".getBytes(Charset.defaultCharset())).toString();
        managerAuth = "Basic " + Base64.getEncoder().encodeToString("manager:password".getBytes(Charset.defaultCharset())).toString();
    }

    @Test
    public void testGetAllDogs() throws Exception {
        mockMvc.perform(get("/dogs").header("Authorization", userAuth))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"))
                .andDo(print());

        verify(dogService, times(1)).getAll();
    }

    @Test
    public void testGetDogByIdUnauthorizedUser() throws Exception {
        mockMvc.perform(get("/dog?id=1").header("Authorization", userAuth))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetDogById() throws Exception {
        mockMvc.perform(get("/dog?id=1").header("Authorization", managerAuth))
                .andExpect(status().isOk());

        verify(dogService, times(1)).getById(1L);
    }

}
