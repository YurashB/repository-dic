package com.example.utils.mappers;

import com.example.dtos.CarRequestDto;
import com.example.dtos.CarResponseDto;
import com.example.models.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toModel(CarRequestDto carRequestDto);

    CarResponseDto toResponseDto(Car car);
}
