package org.example.controllers;

import java.util.List;

import javax.validation.Valid;

import org.example.models.Car;
import org.example.services.CarService;
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

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable String id) {
        return carService.getById(Integer.parseInt(id));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return carService.delete(id);
    }

    @PostMapping()
    public Car postCar(@Valid @RequestBody Car car) {
        return carService.create(car);
    }

    @PatchMapping("/{id}")
    public Car delete(@Valid @RequestBody Car car, @PathVariable int id) {
        return carService.update(car, id);
    }

}