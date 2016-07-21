package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	ObjectMapper mapper;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	

	/**
	@StreamListener(ChannelDefinition.topic)
	public void readCars(Car message) {
        String jsonCar = "";
        try {
                jsonCar = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
        } catch (Exception ex) {}
        
		System.out.println(jsonCar);
    }
**/
}
