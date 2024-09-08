package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarServiceImpl;

import java.util.List;

@Controller
public class CarController {

    @GetMapping("cars")
    public String printCarTable(ModelMap model, @RequestParam(value = "count", required = false) String strCount) {
        List<Car> cars = new CarServiceImpl().listCars();
        if(strCount == null){
            model.addAttribute("table", cars);
        }
        else {
            int count = Integer.parseInt(strCount);
            if(count<5){
                model.addAttribute("table", cars.subList(0, count));
            } else {
                model.addAttribute("table", cars);
            }

        }
        return "carIndex";
    }
}
