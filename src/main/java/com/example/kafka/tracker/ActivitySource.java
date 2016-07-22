package com.example.kafka.tracker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface ActivitySource {
	public String TOPIC = "activitySource";
	
	@Output(ActivitySource.TOPIC)
	MessageChannel sendTracking();
}
