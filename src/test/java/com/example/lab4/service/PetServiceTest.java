package com.example.lab4.service;

import com.example.lab4.model.Pet;
import com.example.lab4.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void testCreatePet() {
        Pet pet = new Pet(null, "Buddy", "available");
        Pet savedPet = new Pet(1L, "Buddy", "available");
        when(petRepository.save(pet)).thenReturn(savedPet);

        Pet result = petService.createPet(pet);
        assertEquals(1L, result.getId());
        assertEquals("Buddy", result.getName());
    }
}
