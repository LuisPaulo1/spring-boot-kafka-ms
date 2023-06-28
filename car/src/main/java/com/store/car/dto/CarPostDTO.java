package com.store.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPostDTO {

	private String model;

	private String brand;

	private Double price;

	private String description;

	private String engineVersion;

	private String city;

	private Long ownerId;

	private String ownerName;

	private String ownerType;

	private String contact;

}
