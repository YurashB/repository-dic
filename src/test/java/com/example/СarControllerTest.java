package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controllers.CarController;
import com.example.dtos.CarResponseDto;
import com.example.exceptions.EntityNotFoundException;
import com.example.handlers.CarExceptionHandler;
import com.example.models.Car;
import com.example.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class СarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setControllerAdvice(new CarExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void getAll() throws Exception {
        ArrayList<CarResponseDto> cars = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            cars.add(new CarResponseDto(i, "Brand " + i, "Model " + i, i * 110,
                    LocalDate.now().minusYears(i)));
        }
        String responseCars = objectMapper.writeValueAsString(cars);
        when(carService.getAll()).thenReturn(cars);

        mockMvc.perform(get("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(responseCars))
                .andExpect(status().isOk());
    }

    @Test
    void getById_ValidId() throws Exception {
        CarResponseDto responseCar = new CarResponseDto(1, "Brand " + 1, "Model  " + 1, 1 * 110,
                LocalDate.now().minusYears(1));
        String responseCarString = objectMapper.writeValueAsString(responseCar);

        when(carService.getById(1))
                .thenReturn(responseCar);

        mockMvc.perform(get("/api/cars/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(responseCarString))
                .andExpect(status().isOk());
    }

    @Test
    void getById_InvalidId() throws Exception {
        when(carService.getById(99999))
                .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/api/cars/99999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException));
    }

    @Test
    void delete_InvalidId() throws Exception {
        when(carService.delete(99999))
                .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(delete("/api/cars/99999"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException));
    }

    @Test
    void postCar_InvalidCar() throws Exception {
        Car responseCar = new Car(1, null, null, (1) * 110,
                LocalDate.now().plusDays(100));
        String responseCarString = objectMapper.writeValueAsString(responseCar);

        mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(responseCarString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void patchCar_InvalidCar() throws Exception {
        Car responseCar = new Car(1, null, null, null,
                LocalDate.now().plusYears(1));
        String responseCarString = objectMapper.writeValueAsString(responseCar);

        mockMvc.perform(patch("/api/cars/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(responseCarString))
                .andExpect(status().isBadRequest());
    }
}
