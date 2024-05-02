package com.example.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.models.CarShop;

@Repository
public interface CarShopRepository extends MongoRepository<CarShop, String> {

}
