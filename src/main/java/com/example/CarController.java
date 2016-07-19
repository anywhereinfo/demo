package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domain.Car;

@RestController
@RequestMapping("/cars")
public class CarController {

        @Autowired
        private CarSource source;


        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> saveCar(@RequestBody Car car ) {
                source.sendCar(car);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(ServletUriComponentsBuilder
                                .fromCurrentRequest().path("/{id}")
                                .buildAndExpand(car.getId()).toUri());
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

        }


}
