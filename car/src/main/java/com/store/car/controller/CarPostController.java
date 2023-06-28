package com.store.car.controller;

import java.util.List;

import com.store.car.dto.CarPostDTO;
import com.store.car.service.CarPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class CarPostController {

	@Autowired
	private CarPostService carPostService;

	@GetMapping("/cars")
	public ResponseEntity<List<CarPostDTO>> getCarSales(){
		return ResponseEntity.status(HttpStatus.FOUND).body(carPostService.getCarSales());
	}

	@PutMapping("/car/{id}")
	public ResponseEntity<Void> changeCarSale(@RequestBody CarPostDTO carPostDTO, @PathVariable("id") String id){
		carPostService.changeCarSale(carPostDTO, Long.valueOf(id));
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/car/{id}")
	public ResponseEntity<Void> removeCarSale(@PathVariable("id") String id){
		carPostService.removeCarSale(Long.valueOf(id));
		return ResponseEntity.ok().build();
	}

}
