package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	ObjectMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) throws UnknownHostException {
	      SpringApplication app = new SpringApplication(DemoApplication.class);
	        Environment env = app.run(args).getEnvironment();
	        log.info("\n----------------------------------------------------------\n\t" +
	                "Application '{}' is running! Access URLs:\n\t" +
	                "Local: \t\thttp://127.0.0.1:{}\n\t" +
	                "External: \thttp://{}:{}\n----------------------------------------------------------",
	            env.getProperty("spring.application.name"),
	            env.getProperty("server.port"),
	            InetAddress.getLocalHost().getHostAddress(),
	            env.getProperty("server.port"));
	        log.info("KAFKA ADDRESS: " + env.getProperty("spring.cloud.stream.kafka.binder.brokers"));
	        log.info("ZOOKEEPER ADDRESS: " + env.getProperty("spring.cloud.stream.kafka.binder.zkNodes"));
	}	
}
