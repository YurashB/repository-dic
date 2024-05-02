package com.example.utils.mappers;

import org.mapstruct.Mapper;

import com.example.dtos.CarShopRequestDto;
import com.example.dtos.CarShopResponseDto;
import com.example.models.CarShop;


@Mapper(componentModel = "spring")
public interface CarShopMapper {

    CarShop toModel(CarShopRequestDto carShopRequestDto);

    CarShopResponseDto toResponseDto(CarShop carShop);
}
