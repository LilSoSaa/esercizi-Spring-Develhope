package com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Controller;

/*crea nuova Car
restituisce la lista di tutte le Car
restituisce una singola Car - se id non è presente in db (usa existsById()), restituisce 404
aggiorna type della Car specifica, identificata da id e passando query param - se id non è presente in db (usa existsById()), restituisce 404
cancella la Car specifica - se non presente, 404
cancella tutte le Cars in db */

import com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Entities.Car;
import com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Enumerates.CarType;
import com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {


    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public ResponseEntity<Car> createCar (@RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        return ResponseEntity.ok(savedCar);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            return ResponseEntity.ok(optionalCar.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarType(@PathVariable Long id, @RequestParam CarType type) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Car car = carRepository.findById(id).get();
        car.setType(type);
        return ResponseEntity.ok(carRepository.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCars() {
        carRepository.deleteAll();
        return ResponseEntity.ok().build();
    }


}
