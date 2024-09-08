package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {
    @Override
    public void addCar(Car car) {

    }

    @Override
    public List<Car> listCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Supra", 1500));
        cars.add(new Car("Subaru", "Impreza", 300));
        cars.add(new Car("Nissan", "Skyline", 500));
        cars.add(new Car("Nissan", "370z", 400));
        cars.add(new Car("Porsche", "911", 420));
        return cars;
    }
}