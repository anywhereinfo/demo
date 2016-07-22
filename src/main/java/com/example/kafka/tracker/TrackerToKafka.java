package com.example.kafka.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.example.domain.tracker.CarActivityDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(ActivitySource.class)
public class TrackerToKafka {
	
	private static final Logger log = LoggerFactory.getLogger(TrackerToKafka.class);

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ActivitySource output;
	
	
	public void sendToKafka(CarActivityDTO dto) {
		log.debug("Received in TrackerToKafka: " + dto);
		Message<?> message = buildDefaultMessage(dto);
		output.sendTracking().send(message);
		log.debug("sent to kafka" + message);
	}
	
	
    private Message<?> buildDefaultMessage(CarActivityDTO dto)
    {
    	String carActivity = "";
    	try {
    		carActivity = mapper.writeValueAsString(dto);
    	} catch (Exception e) {}
    	
    	return MessageBuilder.withPayload(carActivity)
		.setHeader(MessageHeaders.CONTENT_TYPE, "application/json")
		.build();
    }
}
