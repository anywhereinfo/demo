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
	
    public void sendCar(Car car, String contentType) {
    	
  //  	Message<?> message = buildDefaultMessage(car, contentType);
    	Message<?> message = buildAvroMessage(car);
        System.out.println(message);
        output.output().send(message);  
   }

    private Message<?> buildAvroMessage(Car car) {
    	AvroCar avroCar = AvroCar.newBuilder()
    							.setId(car.getId())
    							.setYear(car.getYear())
    							.setMake(car.getMake())
    							.setModel(car.getModel())
    							.setEngine(car.getEngine())
    							.build();
    	byte[] payload = null;
    	
    	try {
    		payload = new AvroCodec().encode(avroCar);
    	} catch (Exception ex) {}
    	
    	return MessageBuilder.withPayload(payload)
    			.setHeader(MessageHeaders.CONTENT_TYPE, "binary/avro")
    			.build();  	
    	
    }
    
    private Message<?> buildDefaultMessage(AvroCar car, String contentType)
    {
    	String jsonCar = "";
    	try {
    		jsonCar = mapper.writeValueAsString(car);
    	} catch (Exception e) {}
    	
    	return MessageBuilder.withPayload(jsonCar)
		.setHeader(MessageHeaders.CONTENT_TYPE, contentType)
		.build();
    }
}
