package com.example.services;

import java.util.List;

import com.example.dtos.CarShopRequestDto;
import com.example.dtos.CarShopResponseDto;


public interface CarShopService {

    List<CarShopResponseDto> getAll();

    CarShopResponseDto create(CarShopRequestDto carShopRequestDto);

    CarShopResponseDto getById(String id);

    boolean delete(String id);

    CarShopResponseDto update(CarShopRequestDto carShopRequestDto, String id);
}
