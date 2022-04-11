package com.schoolofnet.SpringMaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController
{
    @Autowired
    private CarRepository carRepository;

    public CarController(CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Car> getAllCars()
    {
        return this.carRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Car> getCarById(@PathVariable("id") Long id)
    {
        return this.carRepository.findById(id);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public Car create(@RequestBody Car car)
    {
        return this.carRepository.save(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id)
    {
        this.carRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Optional<Car> update(@PathVariable("id") Long id, @RequestBody Car car)
    {
        Optional<Car> updatedCar = this.carRepository.findById(id).map(record -> {
            record.setName(car.getName());
            record.setColor(car.getColor());
            record.setYear(car.getYear());
            Car updated = this.carRepository.save(record);
            return updated;
        });
        return updatedCar;
    }
}
