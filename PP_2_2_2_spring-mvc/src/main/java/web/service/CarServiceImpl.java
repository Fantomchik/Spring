package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarDao;
import web.model.Car;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarDao carDao;

    //@Transactional
    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    //@Transactional()
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