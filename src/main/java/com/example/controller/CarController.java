package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.example.domain.Car;
import com.example.domain.CarV2;
import com.example.kafka.CarSender;


@RestController
public class CarController {
        
		private static final String contentType = "binary/avro";
		
        @Autowired
        CarSender sender;
        private static final Logger log = LoggerFactory.getLogger(CarController.class);

        @Timed
        @RequestMapping(path="cars", method = RequestMethod.POST, consumes = {"application/json"})
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void saveCar(@RequestBody Car car) {
        	log.debug("Received car from browser: " + car);
        	sender.sendCar(car, contentType);
        }

        @Timed
        @RequestMapping(path="/cars/v2",  method = RequestMethod.POST, consumes = {"application/json"})
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void saveCarV2(@RequestBody CarV2 car) {
        	log.debug("Received car from browser: " + car);
        	sender.sendCar(car, contentType);
        }

}
