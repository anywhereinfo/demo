package com.example.domain.tracker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarActivityDTO {
	@JsonProperty("carID")
	public String carID;
	
	public String time;
	
	 @Override
	    public String toString() {
	        return "CarActivityDTO{" +
	            "carID='" + carID + '\'' +
	            ", time='" + time + '\'' +
	            '}';
	    }
}
