package org.example.services;

import java.util.List;

import org.example.models.Car;

public interface CarService {

    List<Car> getAll();

    Car create(Car car);

    Car getById(int id);

    boolean delete(int id);

    Car update(Car car, int id);
}
