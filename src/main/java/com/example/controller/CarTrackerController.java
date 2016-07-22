package com.example.controller;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.domain.tracker.CarActivityDTO;
import com.example.kafka.tracker.TrackerToKafka;

@Controller
public class CarTrackerController {
	
	@Autowired
	TrackerToKafka toKafka;
	
    private static final Logger log = LoggerFactory.getLogger(CarTrackerController.class);	
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@MessageMapping("/activity")
    @SendTo("/topic/tracker")
    public CarActivityDTO sendActivity(@Payload CarActivityDTO dto) {
		Instant instant = Instant.ofEpochMilli(Calendar.getInstance().getTimeInMillis());
		dto.time = dateTimeFormatter.format(ZonedDateTime.ofInstant(instant, ZoneOffset.systemDefault()));
    	log.debug("Sending car tracking data {}", dto);
        toKafka.sendToKafka(dto);
        return dto;
    }
}
