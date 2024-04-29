package org.example.utils.mappers;

import org.example.dtos.CarRequestDto;
import org.example.dtos.CarResponseDto;
import org.example.models.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toModel(CarRequestDto carRequestDto);

    CarResponseDto toResponseDto(Car car);
}
