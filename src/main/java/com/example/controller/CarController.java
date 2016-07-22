package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Car;
import com.example.kafka.CarSender;

@RestController
@RequestMapping("/cars")
public class CarController {
        
        @Autowired
        CarSender sender;
        private static final Logger log = LoggerFactory.getLogger(CarController.class);

        @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void saveCar(@RequestBody Car car, 
        					@RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType) {
        	log.debug("Received car from browser: " + car);
        	sender.sendCar(car, contentType);
        }


}
