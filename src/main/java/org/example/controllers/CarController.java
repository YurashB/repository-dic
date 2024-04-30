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
    public CarResponseDto getById(@PathVariable String id) {
        return carMapper.toResponseDto(carService.getById(Integer.parseInt(id)));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return carService.delete(id);
    }

    @PostMapping()
    public CarResponseDto postCar(@Valid @RequestBody CarRequestDto carRequestDto) {
        Car car = carMapper.toModel(carRequestDto);
        return carMapper.toResponseDto(carService.create(car));
    }

    @PatchMapping("/{id}")
    public CarResponseDto delete(@Valid @RequestBody CarRequestDto carRequestDto, @PathVariable int id) {
        Car car = carMapper.toModel(carRequestDto);
        return carMapper.toResponseDto(carService.update(car, id));
    }

}