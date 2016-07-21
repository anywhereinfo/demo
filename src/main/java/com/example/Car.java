package com.example;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {

	private final String id;
	private final int year;
	private final String make;
	private final String model;
	private final String engine;
	
	public String getId() {
		return id;
	}

	public int getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getEngine() {
		return engine;
	}

	@JsonCreator
	public Car (@JsonProperty("year") int year, @JsonProperty("make") String make,
				@JsonProperty("model") String model, @JsonProperty("engine") String engine)
	{
		this.year = year;
		this.make = make;
		this.model = model;
		this.engine = engine;
		this.id = UUID.randomUUID().toString();
	}
	
}

