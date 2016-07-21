package com.example;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(Sink.class)
public class CarReceiver {

	@ServiceActivator(inputChannel=Sink.INPUT)
	public void loggerSink(Object payload) {
		System.out.println("Received: " + payload);
	}

}