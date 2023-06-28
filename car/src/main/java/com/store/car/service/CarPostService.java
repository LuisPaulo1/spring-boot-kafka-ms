package com.store.car.service;

import com.store.car.dto.CarPostDTO;

import java.util.List;

public interface CarPostService {

    void newPostDetails(CarPostDTO carPostDTO);

    List<CarPostDTO> getCarSales();

    void changeCarSale(CarPostDTO carPostDTO, Long postId);

    void removeCarSale(Long postId);

}
