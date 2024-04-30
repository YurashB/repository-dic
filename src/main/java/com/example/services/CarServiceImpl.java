package com.example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dtos.CarRequestDto;
import com.example.dtos.CarResponseDto;
import com.example.exceptions.EntityNotFoundException;
import com.example.models.Car;
import com.example.repositories.CarRepository;
import com.example.utils.mappers.CarMapper;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarResponseDto> getAll() {
        return carRepository.findAll().stream().map(carMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public CarResponseDto create(CarRequestDto carRequestDto) {
        return carMapper.toResponseDto(carRepository.save(carMapper.toModel(carRequestDto)));
    }

    @Override
    public CarResponseDto getById(int id) {
        return carMapper.toResponseDto(findCar(id));
    }

    @Override
    public boolean delete(int id) {
        carRepository.deleteById(id);
        return true;
    }

    @Override
    public CarResponseDto update(CarRequestDto carRequestDto, int id) {
        return carMapper.toResponseDto(carRepository.findById(id).map(entity -> {
            entity.setBrand(carRequestDto.brand);
            entity.setModel(carRequestDto.model);
            entity.setHorsePower(carRequestDto.horsePower);
            entity.setIntroductionDate(carRequestDto.introductionDate);
            return carRepository.save(entity);
        }).orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " not found")));
    }

    private Car findCar(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " not found"));

    }
}
