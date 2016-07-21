package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
        
        @Autowired
        CarSender sender;

        @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void saveCar(@RequestBody String car, 
        					@RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType) {
        	sender.sendCar(car, contentType);
        }


}
