package web.dao;

import web.model.Car;

import java.util.List;

public interface CarDao {
    void addCar(Car car);
    List<Car> listCars();
}