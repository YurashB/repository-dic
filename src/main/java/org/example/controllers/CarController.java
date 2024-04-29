package org.example.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.example.dtos.CarRequestDto;
import org.example.dtos.CarResponseDto;
import org.example.models.Car;
import org.example.services.CarService;
import org.example.utils.mappers.CarMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping()
    public List<CarResponseDto> getAll() {
        return carService.getAll().stream().map(carMapper::toResponseDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return false;
    }

    @PostMapping()
    public Car postCar(@Valid @RequestBody CarRequestDto carRequestDto) {
        return null;
    }

    @PatchMapping("/{id}")
    public Car delete(@Valid @RequestBody Car car, @PathVariable int id) {
        return null;
    }

}