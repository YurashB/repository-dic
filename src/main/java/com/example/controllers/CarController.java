package com.example.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.CarRequestDto;
import com.example.dtos.CarResponseDto;
import com.example.services.CarService;

@RestController()
@RequestMapping(value = "/api/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public List<CarResponseDto> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public CarResponseDto getById(@PathVariable String id) {
        return carService.getById(Integer.parseInt(id));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return carService.delete(id);
    }

    @PostMapping()
    public CarResponseDto postCar(@Valid @RequestBody CarRequestDto carRequestDto) {
        return carService.create(carRequestDto);
    }

    @PatchMapping("/{id}")
    public CarResponseDto delete(@Valid @RequestBody CarRequestDto carRequestDto, @PathVariable int id) {
        return carService.update(carRequestDto, id);
    }

}