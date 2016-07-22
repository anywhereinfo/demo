package com.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.support.GenericMessage;

import com.example.domain.AvroCar;
import com.example.domain.Car;
import com.example.util.AvroCodec;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableBinding(Sink.class)
public class CarReceiver {

	private static final Logger log = LoggerFactory.getLogger(CarReceiver.class);
	
	@Autowired
	ObjectMapper mapper;
	
	@ServiceActivator(inputChannel=Sink.INPUT)
	public void loggerSink(GenericMessage<byte[]> message) {
		log.debug("Received Generic messsage: " + message);
		
		byte[] payload = message.getPayload();

		AvroCodec codec = new AvroCodec();
		AvroCar payloadCar = null;
		try {
				payloadCar = codec.decode(payload, AvroCar.class); 
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		log.debug("Could Car be converted back to Avro? " + (payloadCar != null));
		
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