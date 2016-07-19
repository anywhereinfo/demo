package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.example.domain.Car;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class CarSource {

        @Autowired @Qualifier("cars")
        private MessageChannel postCars;

        @Autowired
        ObjectMapper mapper;



        public void sendCar(Car car) {
                String jsonCar = "";
                try {
                        jsonCar = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
                } catch (Exception ex) {}

                Message<?> message = MessageBuilder.withPayload(jsonCar)
                                                                .setHeader("content-type", "application/json")
                                                                .build();
                postCars.send(message);
        }

}
