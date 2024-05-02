package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controllers.CarShopController;
import com.example.dtos.CarShopResponseDto;
import com.example.exceptions.EntityNotFoundException;
import com.example.handlers.CarExceptionHandler;
import com.example.services.CarShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class CarShopControllerTest {
    @Mock
    private CarShopService service;

    @InjectMocks
    private CarShopController carShopController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carShopController)
                .setControllerAdvice(new CarExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void getAll() throws Exception {
        List<CarShopResponseDto> carResponseDtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            carResponseDtos.add(new CarShopResponseDto("someUUIDsomeUUID", "Name", 10, true));
        }

        String responseCarShops = objectMapper.writeValueAsString(carResponseDtos);
        when(service.getAll())
                .thenReturn(carResponseDtos);

        mockMvc.perform(get("/api/car-shops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(responseCarShops))
                .andExpect(status().isOk());
    }

    @Test
    void getById_ValidId() throws Exception {
        CarShopResponseDto carShopResponseDto = new CarShopResponseDto("someUUIDsomeUUID", "Name", 10, true);
        String responseCarShopDTOJson = objectMapper.writeValueAsString(carShopResponseDto);

        when(service.getById("someUUIDsomeUUID"))
                .thenReturn(carShopResponseDto);

        mockMvc.perform(get("/api/car-shops/someUUIDsomeUUID")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(responseCarShopDTOJson))
                .andExpect(status().isOk());
    }

    @Test
    void getById_InvalidId() throws Exception {
        when(service.getById("someUUIDsomeUUID"))
                .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/api/car-shops/someUUIDsomeUUID")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException));
    }

    @Test
    void delete_InvalidId() throws Exception {
        when(service.delete("someUUIDsomeUUID"))
                .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(delete("/api/car-shops/someUUIDsomeUUID"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException));
    }

    @Test
    void postCarShop_InvalidCarShop() throws Exception {
       mockMvc.perform(post("/api/car-shops")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void patchCarShop_InvalidCarShop() throws Exception {
        mockMvc.perform(patch("/api/car-shops/someUUIDsomeUUID")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
