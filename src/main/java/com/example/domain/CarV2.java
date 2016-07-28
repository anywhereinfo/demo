package com.example.domain;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarV2 {

		private final String id;
		private final int manufacturerYear;
		private final String make;
		private final String model;
		private final String review;
		


		public String getId() {
			return id;
		}



		public int getManufacturerYear() {
			return manufacturerYear;
		}



		public String getMake() {
			return make;
		}



		public String getModel() {
			return model;
		}



		public String getReview() {
			return review;
		}



		@JsonCreator
		public CarV2 (@JsonProperty("manufacturerYear") int manufacturerYear, 
					  @JsonProperty("make") String make,
					  @JsonProperty("model") String model,
					  @JsonProperty("review") String review)
		{
			this.manufacturerYear = manufacturerYear;
			this.make = make;
			this.model = model;
			this.review = review;
			this.id = UUID.randomUUID().toString();
		}


}
