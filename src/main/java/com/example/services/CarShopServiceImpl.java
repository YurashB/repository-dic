package com.example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dtos.CarShopRequestDto;
import com.example.dtos.CarShopResponseDto;
import com.example.exceptions.EntityNotFoundException;
import com.example.models.CarShop;
import com.example.repositories.CarShopRepository;
import com.example.utils.mappers.CarShopMapper;

@Service
public class CarShopServiceImpl implements CarShopService {

    private final CarShopRepository carShopRepository;
    private final CarShopMapper carShopMapper;

    public CarShopServiceImpl(CarShopRepository carShopRepository, CarShopMapper carShopMapper) {
        this.carShopRepository = carShopRepository;
        this.carShopMapper = carShopMapper;
    }

    @Override
    public List<CarShopResponseDto> getAll() {
        return carShopRepository.findAll()
                .stream()
                .map(carShopMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarShopResponseDto create(CarShopRequestDto carShopRequestDto) {
        CarShop carShop = carShopMapper.toModel(carShopRequestDto);

        return carShopMapper.toResponseDto(carShopRepository.save(carShop));
    }

    @Override
    public CarShopResponseDto getById(String id) {
        return carShopMapper.toResponseDto(this.findCarShopBy(id));
    }

    @Override
    public boolean delete(String id) {
        carShopRepository.delete(this.findCarShopBy(id));
        return true;
    }

    @Override
    public CarShopResponseDto update(CarShopRequestDto carShopRequestDto, String id) {
        return carShopMapper.toResponseDto(
                carShopRepository.findById(id)
                        .map(entity -> {
                            entity.setName(carShopRequestDto.name);
                            entity.setStuffAmount(carShopRequestDto.stuffAmount);
                            entity.setWorking(carShopRequestDto.isWorking);
                            return carShopRepository.save(entity);
                        })
                        .orElseThrow(() -> new EntityNotFoundException("StudentDocument with id " + id + " not found"))
        );
    }

    private CarShop findCarShopBy(String id) {
        return carShopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car shop with id " + id + " not found"));
    }
}
