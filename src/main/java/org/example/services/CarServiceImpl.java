package org.example.services;

import java.util.List;

import org.example.models.Car;
import org.example.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car create(Car car) {
        return null;
    }

    @Override
    public Car getById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Car update(Car car, int id) {
        return null;
    }
}
