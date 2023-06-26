package com.portal.api.service;

import java.util.List;

import com.portal.api.dto.CarPostDTO;

public interface CarPostStoreService {
	List<CarPostDTO> getCarForSales();

	void changeCarForSale(CarPostDTO carPost, String id);

	void removeCarForSale(String id);
}
