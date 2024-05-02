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

import com.example.dtos.CarShopRequestDto;
import com.example.dtos.CarShopResponseDto;
import com.example.services.CarShopService;

@RestController()
@RequestMapping(value = "/api/car-shops", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarShopController {
    private final CarShopService carShopService;

    public CarShopController(CarShopService carShopService) {
        this.carShopService = carShopService;
    }


    @GetMapping()
    public List<CarShopResponseDto> getAll() {
        return carShopService.getAll();
    }

    @GetMapping("/{id:[a-zA-Z0-9]*}")
    public CarShopResponseDto getById(@PathVariable String id) {
        return carShopService.getById(id);
    }

    @DeleteMapping("/{id:[a-zA-Z0-9]*}")
    public boolean delete(@PathVariable String id) {
        return carShopService.delete(id);
    }

    @PostMapping()
    public CarShopResponseDto postCarShop(@Valid @RequestBody CarShopRequestDto carShopRequestDto) {
        return carShopService.create(carShopRequestDto);
    }

    @PatchMapping("/{id:[a-zA-Z0-9]*}")
    public CarShopResponseDto delete(@Valid @RequestBody CarShopRequestDto carShopRequestDto, @PathVariable String id) {
        return carShopService.update(carShopRequestDto, id);
    }
}
