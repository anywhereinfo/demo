package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.support.GenericMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableBinding(Sink.class)
public class CarReceiver {

	@Autowired
	ObjectMapper mapper;
	
	@ServiceActivator(inputChannel=Sink.INPUT)
	public void loggerSink(GenericMessage<byte[]> message) {
		System.out.println("Received : " + message);
		byte[] payload = message.getPayload();

		AvroCodec codec = new AvroCodec();
		AvroCar payloadCar = null;
		try {
				payloadCar = codec.decode(payload, AvroCar.class); 
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		Car car = new Car(payloadCar.getYear(),
						  payloadCar.getMake(),
						  payloadCar.getModel(),
						  payloadCar.getEngine());
		String jsonCar = "NULL CAR";
		try{
			jsonCar =  mapper.writeValueAsString(car);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Received: " + jsonCar);
	}
	

}