package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(Source.class)
public class CarSender {

	@Autowired
	private Source output;
	
    @Autowired
    ObjectMapper mapper;
	
    public void sendCar(String car, String contentType) {
    	Message<?> message = MessageBuilder.withPayload(car)
				.setHeader(MessageHeaders.CONTENT_TYPE, contentType)
				.build();
        System.out.println(message);
        output.output().send(message);  
   }
}
