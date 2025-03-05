package com.example.lab4.controller;

import com.example.lab4.model.Pet;
import com.example.lab4.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    void testGetPetById_NotFound() throws Exception {
        when(petService.getPetById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/pets/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePet() throws Exception {
        Pet pet = new Pet(1L, "Buddy", "available");
        when(petService.createPet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Buddy\",\"status\":\"available\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
