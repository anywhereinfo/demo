package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.example.domain.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableBinding(ChannelMetadata.class)
public class LoggingSink {

        @Autowired
        ObjectMapper mapper;


        @StreamListener("cars")
    public void log(Car message) {
                String jsonCar = "";
                try {
                        jsonCar = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
                } catch (Exception ex) {}

        System.out.println(jsonCar);
    }
}
