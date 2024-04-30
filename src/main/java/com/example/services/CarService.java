package com.example.services;

import java.util.List;

import com.example.dtos.CarRequestDto;
import com.example.dtos.CarResponseDto;

public interface CarService {

    List<CarResponseDto> getAll();

    CarResponseDto create(CarRequestDto carRequestDto);

    CarResponseDto getById(int id);

    boolean delete(int id);

    CarResponseDto update(CarRequestDto carRequestDto, int id);
}
