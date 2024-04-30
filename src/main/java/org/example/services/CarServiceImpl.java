package org.example.services;

import java.util.List;

import org.example.exceptions.EntityNotFoundException;
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
        return carRepository.save(car);
    }

    @Override
    public Car getById(int id) {
        return findCar(id);
    }

    @Override
    public boolean delete(int id) {
        carRepository.deleteById(id);
        return true;
    }

    @Override
    public Car update(Car car, int id) {
        return carRepository.findById(id).map(entity -> {
            entity.setBrand(car.getBrand());
            entity.setModel(car.getModel());
            entity.setHorsePower(car.getHorsePower());
            entity.setIntroductionDate(car.getIntroductionDate());
            return carRepository.save(entity);
        }).orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " not found"));
    }

    private Car findCar(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " not found"));

    }
}
